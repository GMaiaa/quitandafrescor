package com.example.quitandafrescor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.quitandafrescor.dto.CartResponseDTO;
import com.example.quitandafrescor.dto.ItemCartRequestDTO;
import com.example.quitandafrescor.dto.ItemCartResponseDTO;
import com.example.quitandafrescor.dto.ItemCartUpdateDTOReturn;
import com.example.quitandafrescor.dto.OrderRequestDTO;
import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.ItemCart;
import com.example.quitandafrescor.model.Order;
import com.example.quitandafrescor.model.OrderItem;
import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.CartRepository;
import com.example.quitandafrescor.repository.ItemCartRepository;
import com.example.quitandafrescor.repository.OrderItemRepository;
import com.example.quitandafrescor.repository.OrderRepository;
import com.example.quitandafrescor.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService implements ICartService {

    Logger logger = LoggerFactory.getLogger(CartService.class);

    private ProductRepository productRepository;
    private ItemCartRepository itemCartRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private OrderItemRepository orderItemRepository;
    private EmailService emailService;

    public CartService(ProductRepository productRepository, ItemCartRepository itemCartRepository,
            CartRepository cartRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository,
            EmailService emailService) {
        this.productRepository = productRepository;
        this.itemCartRepository = itemCartRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.emailService = emailService;

    }

    private float calculateTotalCartValue() {
        List<ItemCart> items = itemCartRepository.findAll();
        float totalValue = 0;
        for (ItemCart item : items) {
            totalValue += item.getSubTotalValue();
        }
        totalValue = Math.round(totalValue * 100.0) / 100.0f;
        return totalValue;
    }

    private void recalculateCartTotalValue(Cart cart) {
        cart.setTotalValue(calculateTotalCartValue());
        cartRepository.save(cart);
    }

    private Order createOrder(OrderRequestDTO orderDto) {
        Order order = new Order();
        order.setClient(orderDto.client());
        order.setCpf(orderDto.cpf());
        order.setEmail(orderDto.email());
        order.setCep(orderDto.cep());
        order.setAdressNumber(orderDto.adressNumber());
        order.setAdress(orderDto.adress());
        order.setComplement(orderDto.complement());
        order.setPhoneNumber(orderDto.phoneNumber());
        order.setPaymentMethod(orderDto.paymentMethod());
        order.setMoneyChange(orderDto.moneyChange());
        order.setStatus("🟡 Pendente");
        return order;
    }

    private void sendConfirmationEmail(OrderRequestDTO orderDto) {
        String subject = "Confirmação de Compra";
        String text = "Olá " + orderDto.client() + ",\n\nSua compra foi confirmada com sucesso!";
        emailService.sendConfirmationEmail(orderDto.email(), subject, text);
    }

    private void createOrderItemsAndDeleteCartItems(Order order, Cart cart) {
        List<ItemCart> items = new ArrayList<>(cart.getItens());
        for (ItemCart itemCart : items) {
            OrderItem orderItem = createOrderItem(order, itemCart);
            orderItemRepository.save(orderItem);
            updateProductStock(itemCart);
            cart.getItens().remove(itemCart);
            itemCart.setCart(null);
            itemCartRepository.save(itemCart);
            itemCartRepository.delete(itemCart);
        }
    }

    private OrderItem createOrderItem(Order order, ItemCart itemCart) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProductName(itemCart.getProduct().getName());
        orderItem.setProductValue(itemCart.getProductValue());
        orderItem.setQuantity(itemCart.getQuantity());
        orderItem.setSubTotalValue(itemCart.getSubTotalValue());
        return orderItem;
    }

    private void updateProductStock(ItemCart itemCart) {
        Product product = itemCart.getProduct();
        int newAmount = product.getAmount() - itemCart.getQuantity();
        if (newAmount < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade insuficiente em estoque");
        }
        product.setAmount(newAmount);
        productRepository.save(product);
    }

    private void createNewCart() {
        Cart newCart = new Cart();
        newCart.setTotalValue(0f);
        cartRepository.save(newCart);
    }

    private Product getProduct(Long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (prod.isPresent()) {
            return prod.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    private Cart createCart() {
        List<Cart> carts = cartRepository.findAll();
        if (carts.isEmpty() || carts.get(carts.size() - 1).getOrder() != null) {
            Cart cart = new Cart();
            cart.setTotalValue(0f);
            cartRepository.save(cart);
            return cart;
        } else {
            return carts.get(carts.size() - 1);
        }
    }

    private ItemCart getExistingItem(Cart cart, Product product) {
        Optional<ItemCart> existingItem = cart.getItens().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();
        return existingItem.orElse(null);
    }

    private void addNewItemToCart(Cart cart, Product product) {
        ItemCart item = new ItemCart();
        item.setProduct(product);
        item.setProductValue(product.getValue());
        item.setQuantity(1);
        item.setSubTotalValue(item.getProductValue() * item.getQuantity());
        item.setCart(cart);
        itemCartRepository.save(item);
        cart.getItens().add(item);
    }

    @Transactional
    public ResponseEntity<Void> confirmPurchase(OrderRequestDTO orderDto) {
        Cart cart = cartRepository.findFirstByOrderByIdDesc();
        if (cart == null || cart.getItens().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não encontrado ou está vazio");
        }

        try {
            Order order = createOrder(orderDto);
            order.setCart(cart);
            orderRepository.save(order);

            sendConfirmationEmail(orderDto);

            createOrderItemsAndDeleteCartItems(order, cart);

            createNewCart();

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Erro ao confirmar a compra", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao confirmar a compra");
        }
    }

    public ResponseEntity<CartResponseDTO> getAllCartItems() {
        List<ItemCart> items = itemCartRepository.findAll();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            float totalValue = calculateTotalCartValue();
            List<ItemCartResponseDTO> itemDtos = items.stream()
                    .map(item -> new ItemCartResponseDTO(item, item.getProduct()))
                    .collect(Collectors.toList());
            CartResponseDTO response = new CartResponseDTO(itemDtos, totalValue);
            return ResponseEntity.ok(response);
        }
    }

    @Transactional
    public ResponseEntity<Void> addCart(Long id) {
        Product product = getProduct(id);
        Cart cart = createCart();
        ItemCart existingItem = getExistingItem(cart, product);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            existingItem.setSubTotalValue(existingItem.getProductValue() * existingItem.getQuantity());
            itemCartRepository.save(existingItem);
        } else {
            addNewItemToCart(cart, product);
        }

        recalculateCartTotalValue(cart);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ItemCartResponseDTO> getCartById(Long id) {
        Optional<ItemCart> itemOpt = itemCartRepository.findById(id);
        if (itemOpt.isPresent()) {
            ItemCart item = itemOpt.get();
            Product product = item.getProduct();
            ItemCartResponseDTO response = new ItemCartResponseDTO(item, product);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<ItemCartUpdateDTOReturn> updateItemCartQuantity(Long id, ItemCartRequestDTO request) {
        Optional<ItemCart> itemOpt = itemCartRepository.findById(id);
        if (itemOpt.isPresent()) {
            ItemCart item = itemOpt.get();
            item.setQuantity(request.quantity());
            item.setSubTotalValue(item.getProductValue() * item.getQuantity());
            itemCartRepository.save(item);
            Product product = item.getProduct();
            ItemCartUpdateDTOReturn response = new ItemCartUpdateDTOReturn(product, item);

            Cart cart = item.getCart();
            recalculateCartTotalValue(cart);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteItemCart(Long id) {
        Optional<ItemCart> itemOpt = itemCartRepository.findById(id);
        if (itemOpt.isPresent()) {
            ItemCart item = itemOpt.get();

            Cart cart = item.getCart();
            recalculateCartTotalValue(cart);

            itemCartRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

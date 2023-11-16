package com.example.quitandafrescor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.example.quitandafrescor.repository.ProductRepository;
import com.example.quitandafrescor.repository.OrderRepository;

@RestController
@RequestMapping("cart")
public class CartController {

    private ProductRepository productRepository;

    private ItemCartRepository itemCartRepository;

    private OrderRepository orderRepository;

    private CartRepository cartRepository;

    private OrderItemRepository orderItemRepository;

    public CartController(ProductRepository productRepository, ItemCartRepository itemCartRepository,
            CartRepository cartRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.itemCartRepository = itemCartRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
@PostMapping("/confirmPurchase")
public ResponseEntity<Void> confirmPurchase(@RequestBody OrderRequestDTO orderDto) {
    // Busca o último carrinho de compras do repositório
    List<Cart> carts = cartRepository.findAll();
    if (carts.isEmpty()) {
        return ResponseEntity.badRequest().build();
    }
    Cart cart = carts.get(carts.size() - 1);

    // Cria um novo pedido a partir do DTO
    Order order = new Order();
    order.setClient(orderDto.client());
    order.setCpf(orderDto.cpf());
    order.setEmail(orderDto.email());
    order.setCep(orderDto.cep());
    order.setAdressNumber(orderDto.adressNumber());
    order.setPhoneNumber(orderDto.phoneNumber());
    order.setPaymentMethod(orderDto.paymentMethod());
    order.setMoneyChange(orderDto.moneyChange());
    order.setStatus("🟡 Pendente"); // Define o status do pedido como "Waiting"

    // Associa o carrinho ao pedido e salva o pedido
    order.setCart(cart);
    orderRepository.save(order);

    // Cria um OrderItem para cada ItemCart no carrinho e deleta o ItemCart
    List<ItemCart> items = new ArrayList<>(cart.getItens());
    for (ItemCart itemCart : items) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProductName(itemCart.getProduct().getName());
        orderItem.setProductValue(itemCart.getProductValue());
        orderItem.setQuantity(itemCart.getQuantity());
        orderItem.setSubTotalValue(itemCart.getSubTotalValue());
        orderItemRepository.save(orderItem);

        // Atualiza o estoque do produto
        Product product = itemCart.getProduct();
        int newAmount = product.getAmount() - itemCart.getQuantity();
        if (newAmount < 0) {
            return ResponseEntity.badRequest().build(); // Retorna um erro se a nova quantidade for negativa
        }
        product.setAmount(newAmount);
        productRepository.save(product);

        cart.getItens().remove(itemCart); // Remove a associação do item com o carrinho
        itemCart.setCart(null); // Remove a associação do carrinho com o item
        itemCartRepository.save(itemCart); // Salva o item sem a associação
        itemCartRepository.delete(itemCart); // Agora você pode deletar o item
    }

    // Cria um novo carrinho para compras futuras
    Cart newCart = new Cart();
    newCart.setTotalValue(0f);
    cartRepository.save(newCart);

    // Retorna apenas o status OK sem corpo
    return ResponseEntity.ok().build();
}

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAllCartItems")
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/addCart/{id}")
    public ResponseEntity<Void> addCart(@PathVariable Long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (prod.isPresent()) {
            Product product = prod.get();

            // Busca o carrinho de compras do repositório
            List<Cart> carts = cartRepository.findAll();

            // Se não houver nenhum carrinho ou o último carrinho já estiver associado a um
            // pedido, cria um novo carrinho
            Cart cart;
            if (carts.isEmpty() || carts.get(carts.size() - 1).getOrder() != null) {
                cart = new Cart();
                cart.setTotalValue(0f);
                cartRepository.save(cart);
            } else {
                cart = carts.get(carts.size() - 1);
            }

            // Verifica se o produto já está no carrinho
            Optional<ItemCart> existingItem = cart.getItens().stream()
                    .filter(item -> item.getProduct().getId().equals(product.getId()))
                    .findFirst();

            if (existingItem.isPresent()) {
                // Se o produto já está no carrinho, apenas incrementa a quantidade
                ItemCart item = existingItem.get();
                item.setQuantity(item.getQuantity() + 1);
                item.setSubTotalValue(item.getProductValue() * item.getQuantity());
                itemCartRepository.save(item);
            } else {
                // Se o produto não está no carrinho, adiciona um novo item
                ItemCart item = new ItemCart();
                item.setProduct(product);
                item.setProductValue(product.getValue());
                item.setQuantity(1);
                item.setSubTotalValue(item.getProductValue() * item.getQuantity());
                item.setCart(cart); // Associa o item ao carrinho
                itemCartRepository.save(item);
                cart.getItens().add(item); // Atualiza a lista de itens no carrinho
            }

            // Recalcula o valor total do carrinho
            cart.setTotalValue(calculateTotalCartValue());
            cartRepository.save(cart);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ItemCartResponseDTO> getCartById(@PathVariable Long id) {
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ItemCartUpdateDTOReturn> updateItemCartQuantity(@PathVariable Long id,
            @RequestBody ItemCartRequestDTO request) {
        Optional<ItemCart> itemOpt = itemCartRepository.findById(id);
        if (itemOpt.isPresent()) {
            ItemCart item = itemOpt.get();
            item.setQuantity(request.quantity());
            item.setSubTotalValue(item.getProductValue() * item.getQuantity());
            itemCartRepository.save(item);
            Product product = item.getProduct();
            ItemCartUpdateDTOReturn response = new ItemCartUpdateDTOReturn(product, item);

            // Recalcula o valor total do carrinho
            Cart cart = item.getCart();
            cart.setTotalValue(calculateTotalCartValue());
            cartRepository.save(cart);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemCart(@PathVariable Long id) {
        Optional<ItemCart> itemOpt = itemCartRepository.findById(id);
        if (itemOpt.isPresent()) {
            ItemCart item = itemOpt.get();

            // Recalcula o valor total do carrinho antes de excluir o item
            Cart cart = item.getCart();
            cart.setTotalValue(cart.getTotalValue() - item.getSubTotalValue());
            cartRepository.save(cart);

            itemCartRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

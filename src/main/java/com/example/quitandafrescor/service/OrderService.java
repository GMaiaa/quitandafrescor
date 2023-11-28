package com.example.quitandafrescor.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quitandafrescor.dto.OrderResponseDTO;
import com.example.quitandafrescor.dto.OrderUpdateDTO;
import com.example.quitandafrescor.dto.OrderUpdateDTOReturn;
import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.Order;
import com.example.quitandafrescor.model.OrderItem;
import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.OrderRepository;
import com.example.quitandafrescor.repository.ProductRepository;

@Service
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private EmailService emailService;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
            EmailService emailService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<OrderResponseDTO> orderDtos = orders.stream()
                    .map(order -> {
                        Cart cart = order.getCart();
                        return new OrderResponseDTO(order, order.getStatus(), cart.getTotalValue(), cart);
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(orderDtos);
        }
    }

    public ResponseEntity<OrderUpdateDTOReturn> updateOrderStatus(Long id, OrderUpdateDTO orderUpdateDto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            String oldStatus = order.getStatus();
            order.setStatus(orderUpdateDto.status());
            orderRepository.save(order);

            // Se o status do pedido foi alterado para "cancelado", reverter as alterações
            // no estoque
            if (!oldStatus.equals("🔴 Cancelado") && order.getStatus().equals("🔴 Cancelado")) {
                for (OrderItem orderItem : order.getOrderItems()) {
                    Optional<Product> optionalProduct = productRepository
                            .findByNameIgnoreCase(orderItem.getProductName());
                    if (optionalProduct.isPresent()) {
                        Product product = optionalProduct.get();
                        product.setAmount(product.getAmount() + orderItem.getQuantity());
                        productRepository.save(product);
                    }
                }
            }

            // Se o status do pedido foi alterado para "🟠 Preparando", enviar um e-mail
            // para o cliente
            if (order.getStatus().equals("🟠 Preparando")) {
                String subject = "Seu pedido está sendo preparado";
                String text = "Olá " + order.getClient() + ",\n\nSeu pedido já está sendo preparado!";
                emailService.sendConfirmationEmail(order.getEmail(), subject, text);
            }

            // Se o status do pedido foi alterado para "🔵 Entregando", enviar um e-mail
            // para o cliente
            if (order.getStatus().equals("🔵 Entregando")) {
                String subject = "Seu pedido está sendo entregue";
                String text = "Olá " + order.getClient() + ",\n\nSeu pedido está sendo entregue para o endereço "
                        + order.getAdress() + ", número " + order.getAdressNumber() + ".";
                emailService.sendConfirmationEmail(order.getEmail(), subject, text);
            }

            // Se o status do pedido foi alterado para "🟢 Entregue", enviar um e-mail
            // para o cliente
            if (order.getStatus().equals("🟢 Entregue")) {
                String subject = "Seu pedido foi entregue";
                String text = "Olá " + order.getClient() + ",\n\nSeu pedido foi entregue com sucesso!.";
                emailService.sendConfirmationEmail(order.getEmail(), subject, text);
            }

            // Se o status do pedido foi alterado para "🔴 Cancelado", enviar um e-mail
            // para o cliente
            if (order.getStatus().equals("🔴 Cancelado")) {
                String subject = "Seu pedido foi cancelado com sucesso";
                String text = "Olá " + order.getClient() + ",\n\nInformamos que seu pedido foi cancelado com sucesso.";
                emailService.sendConfirmationEmail(order.getEmail(), subject, text);
            }

            return ResponseEntity.ok(new OrderUpdateDTOReturn(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<OrderResponseDTO> getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            Cart cart = order.getCart();
            OrderResponseDTO orderDto = new OrderResponseDTO(order, order.getStatus(), cart.getTotalValue(), cart);
            return ResponseEntity.ok(orderDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<Map<String, Object>>> getMostOrderedProducts() {
        List<Order> orders = orderRepository.findAll();

        // Mapeia os produtos para contabilizar a quantidade pedida
        Map<String, Integer> productCount = new HashMap<>();
        for (Order order : orders) {
            for (OrderItem orderItem : order.getOrderItems()) {
                String productName = orderItem.getProductName();
                productCount.put(productName, productCount.getOrDefault(productName, 0) + orderItem.getQuantity());
            }
        }

        // Ordena os produtos por quantidade pedida (do maior para o menor)
        List<Map<String, Object>> mostOrderedProducts = productCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> {
                    Map<String, Object> productInfo = new HashMap<>();
                    productInfo.put("productName", entry.getKey());
                    productInfo.put("quantitySold", entry.getValue());
                    return productInfo;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(mostOrderedProducts);
    }
}

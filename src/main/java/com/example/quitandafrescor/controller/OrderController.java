package com.example.quitandafrescor.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quitandafrescor.dto.OrderResponseDTO;
import com.example.quitandafrescor.dto.OrderUpdateDTO;
import com.example.quitandafrescor.dto.OrderUpdateDTOReturn;
import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.Order;
import com.example.quitandafrescor.model.OrderItem;
import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.OrderRepository;
import com.example.quitandafrescor.repository.ProductRepository;

@RestController
@RequestMapping("order")
public class OrderController {

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAllOrders")
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/updateOrderStatus/{id}")
    public ResponseEntity<OrderUpdateDTOReturn> updateOrderStatus(@PathVariable Long id,
            @RequestBody OrderUpdateDTO orderUpdateDto) {
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
                    Optional<Product> optionalProduct = productRepository.findByName(orderItem.getProductName());
                    if (optionalProduct.isPresent()) {
                        Product product = optionalProduct.get();
                        product.setAmount(product.getAmount() + orderItem.getQuantity());
                        productRepository.save(product);
                    }
                }
            }

            return ResponseEntity.ok(new OrderUpdateDTOReturn(order));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
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
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("/getMostOrderedProducts")
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

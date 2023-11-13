package com.example.quitandafrescor.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quitandafrescor.dto.OrderResponseDTO;
import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.Order;
import com.example.quitandafrescor.repository.OrderRepository;

@RestController
@RequestMapping("order")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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

}

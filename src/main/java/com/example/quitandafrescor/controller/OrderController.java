package com.example.quitandafrescor.controller;

import java.util.List;
import java.util.Map;

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
import com.example.quitandafrescor.service.IOrderService;

@RestController
@RequestMapping("order")
public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/updateOrderStatus/{id}")
    public ResponseEntity<OrderUpdateDTOReturn> updateOrderStatus(@PathVariable Long id,
            @RequestBody OrderUpdateDTO orderUpdateDto) {
        return orderService.updateOrderStatus(id, orderUpdateDto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getMostOrderedProducts")
    public ResponseEntity<List<Map<String, Object>>> getMostOrderedProducts() {
        return orderService.getMostOrderedProducts();
    }
}
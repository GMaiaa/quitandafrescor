package com.example.quitandafrescor.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.quitandafrescor.dto.OrderResponseDTO;
import com.example.quitandafrescor.dto.OrderUpdateDTO;
import com.example.quitandafrescor.dto.OrderUpdateDTOReturn;

public interface IOrderService {
    ResponseEntity<List<OrderResponseDTO>> getAllOrders();

    ResponseEntity<OrderUpdateDTOReturn> updateOrderStatus(Long id, OrderUpdateDTO orderUpdateDto);

    ResponseEntity<OrderResponseDTO> getOrderById(Long id);

    ResponseEntity<List<Map<String, Object>>> getMostOrderedProducts();
}

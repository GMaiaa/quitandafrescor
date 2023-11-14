package com.example.quitandafrescor.dto;

import com.example.quitandafrescor.model.Order;

public record OrderUpdateDTOReturn(Long id, String status) {

    public OrderUpdateDTOReturn(Order order) {
        this(
                order.getId(),
                order.getStatus());
    }
}

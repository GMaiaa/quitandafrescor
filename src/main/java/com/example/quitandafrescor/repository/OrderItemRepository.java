package com.example.quitandafrescor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quitandafrescor.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

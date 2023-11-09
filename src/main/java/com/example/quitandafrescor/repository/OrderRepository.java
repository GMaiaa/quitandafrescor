package com.example.quitandafrescor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quitandafrescor.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}

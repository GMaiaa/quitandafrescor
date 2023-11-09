package com.example.quitandafrescor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quitandafrescor.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}

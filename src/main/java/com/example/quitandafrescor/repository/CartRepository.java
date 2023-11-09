package com.example.quitandafrescor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quitandafrescor.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
}

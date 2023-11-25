package com.example.quitandafrescor.repository;

import com.example.quitandafrescor.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findFirstByOrderByIdDesc();
}
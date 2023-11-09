package com.example.quitandafrescor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quitandafrescor.model.ItemCart;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {

}

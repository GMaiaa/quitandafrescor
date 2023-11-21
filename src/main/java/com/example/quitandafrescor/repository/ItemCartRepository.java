package com.example.quitandafrescor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quitandafrescor.model.ItemCart;
import com.example.quitandafrescor.model.Product;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {
    List<ItemCart> findAllByProduct(Product product);
}

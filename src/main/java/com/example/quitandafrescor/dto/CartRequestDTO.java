package com.example.quitandafrescor.dto;

import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.Order;
import com.example.quitandafrescor.model.Product;

public record CartRequestDTO(Product product, Cart cart, Order order, Integer quantity) {
    
}

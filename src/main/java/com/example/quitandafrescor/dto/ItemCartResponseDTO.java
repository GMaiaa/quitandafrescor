package com.example.quitandafrescor.dto;

import com.example.quitandafrescor.model.ItemCart;
import com.example.quitandafrescor.model.Product;

public record ItemCartResponseDTO(Long id, String productName, Float productValue, Integer quantity, Float subTotalValue) {
    public ItemCartResponseDTO(ItemCart itemCart, Product product) {
        this(
            itemCart.getId(),
            product.getName(),
            product.getValue(),
            itemCart.getQuantity(),
            itemCart.getSubTotalValue()
        );
    }
}
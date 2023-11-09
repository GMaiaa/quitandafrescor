package com.example.quitandafrescor.dto;

import com.example.quitandafrescor.model.ItemCart;
import com.example.quitandafrescor.model.Product;

public record ItemCartUpdateDTOReturn(Long id, Integer quantity) {

    public ItemCartUpdateDTOReturn(Product product, ItemCart itemCart) {
        this(
                product.getId(),
                itemCart.getQuantity());
    }
}

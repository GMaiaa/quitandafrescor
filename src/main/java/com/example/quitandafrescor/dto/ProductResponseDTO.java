package com.example.quitandafrescor.dto;

import com.example.quitandafrescor.model.Product;

public record ProductResponseDTO(Long id, String name, String description, Float value, String image, Integer amount,
        String category) {

    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getValue(),
                product.getImage(),
                product.getAmount(),
                product.getCategory());
    }
}

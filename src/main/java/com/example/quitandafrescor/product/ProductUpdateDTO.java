package com.example.quitandafrescor.product;

public record ProductUpdateDTO(Long id, String name, String description, Float value, String image, Integer amount, String category) {

}
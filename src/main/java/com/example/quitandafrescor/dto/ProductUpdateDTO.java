package com.example.quitandafrescor.dto;

public record ProductUpdateDTO(Long id, String name, String description, Float value, String image, Integer amount, String category) {

}
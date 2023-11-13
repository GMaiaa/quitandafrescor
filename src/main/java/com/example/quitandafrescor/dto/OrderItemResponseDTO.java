package com.example.quitandafrescor.dto;

public record OrderItemResponseDTO(String productName, Float productValue, Integer quantity, Float subTotalValue) {
}

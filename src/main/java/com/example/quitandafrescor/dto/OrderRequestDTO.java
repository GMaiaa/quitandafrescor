package com.example.quitandafrescor.dto;

import java.util.List;

public record OrderRequestDTO(String client, String cpf, String email, Integer cep, Integer adressNumber,
        String phoneNumber, String paymentMethod, Float moneyChange, String status,
        List<OrderItemResponseDTO> orderItems) {
}
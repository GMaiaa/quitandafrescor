package com.example.quitandafrescor.dto;

import java.util.List;

public record OrderRequestDTO(String client, String cpf, String email, String cep, Integer adressNumber, String adress,
                String complement,
                String phoneNumber, String paymentMethod, Float moneyChange, String status,
                List<OrderItemResponseDTO> orderItems) {
}
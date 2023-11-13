package com.example.quitandafrescor.dto;

public record OrderRequestDTO(String client, String cpf, String email, Integer cep, Integer adressNumber,
        Integer phoneNumber, String paymentMethod, Float moneyChange) {
}

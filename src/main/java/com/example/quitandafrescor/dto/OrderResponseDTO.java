package com.example.quitandafrescor.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.Order;

public record OrderResponseDTO(Long id, String client, String cpf, String email, String deliveryType, String cep,
                String adress,
                Integer adressNumber, String complement, String phoneNumber, String paymentMethod, Float moneyChange,
                Long cartId, Date orderDate,
                String status, Float totalValue, List<OrderItemResponseDTO> items) {
        public OrderResponseDTO(Order order, String status, Float totalValue, Cart cart) {
                this(
                                order.getId(),
                                order.getClient(),
                                order.getCpf(),
                                order.getEmail(),
                                order.getDeliveryType(),
                                order.getCep(),
                                order.getAdress(),
                                order.getAdressNumber(),
                                order.getComplement(),
                                order.getPhoneNumber(),
                                order.getPaymentMethod(),
                                order.getMoneyChange(),
                                cart.getId(),
                                order.getOrderDate(),
                                status,
                                totalValue,
                                // Aqui você precisa adicionar a lógica para converter os itens do pedido em
                                // OrderItemResponseDTO
                                order.getOrderItems().stream()
                                                .map(item -> new OrderItemResponseDTO(item.getProductName(),
                                                                item.getProductValue(),
                                                                item.getQuantity(), item.getSubTotalValue()))
                                                .collect(Collectors.toList()));
        }
}
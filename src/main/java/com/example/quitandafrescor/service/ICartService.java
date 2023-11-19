package com.example.quitandafrescor.service;

import org.springframework.http.ResponseEntity;

import com.example.quitandafrescor.dto.CartResponseDTO;
import com.example.quitandafrescor.dto.ItemCartRequestDTO;
import com.example.quitandafrescor.dto.ItemCartResponseDTO;
import com.example.quitandafrescor.dto.ItemCartUpdateDTOReturn;
import com.example.quitandafrescor.dto.OrderRequestDTO;

public interface ICartService {
    ResponseEntity<Void> confirmPurchase(OrderRequestDTO orderDto);

    ResponseEntity<CartResponseDTO> getAllCartItems();

    ResponseEntity<Void> addCart(Long id);

    ResponseEntity<ItemCartResponseDTO> getCartById(Long id);

    ResponseEntity<ItemCartUpdateDTOReturn> updateItemCartQuantity(Long id, ItemCartRequestDTO request);

    ResponseEntity<Void> deleteItemCart(Long id);
}

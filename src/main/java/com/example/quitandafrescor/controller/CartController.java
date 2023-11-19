package com.example.quitandafrescor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quitandafrescor.dto.CartResponseDTO;
import com.example.quitandafrescor.dto.ItemCartRequestDTO;
import com.example.quitandafrescor.dto.ItemCartResponseDTO;
import com.example.quitandafrescor.dto.ItemCartUpdateDTOReturn;
import com.example.quitandafrescor.dto.OrderRequestDTO;
import com.example.quitandafrescor.service.ICartService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("cart")
public class CartController {

    private ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Transactional
    @PostMapping("/confirmPurchase")
    public ResponseEntity<Void> confirmPurchase(@RequestBody OrderRequestDTO orderDto) {
        return cartService.confirmPurchase(orderDto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAllCartItems")
    public ResponseEntity<CartResponseDTO> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/addCart/{id}")
    public ResponseEntity<Void> addCart(@PathVariable Long id) {
        return cartService.addCart(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ItemCartResponseDTO> getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ItemCartUpdateDTOReturn> updateItemCartQuantity(@PathVariable Long id,
            @RequestBody ItemCartRequestDTO request) {
        return cartService.updateItemCartQuantity(id, request);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemCart(@PathVariable Long id) {
        return cartService.deleteItemCart(id);
    }
}

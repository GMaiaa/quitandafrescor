package com.example.quitandafrescor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.quitandafrescor.dto.ItemCartRequestDTO;
import com.example.quitandafrescor.dto.ItemCartResponseDTO;
import com.example.quitandafrescor.dto.ItemCartUpdateDTOReturn;
import com.example.quitandafrescor.model.ItemCart;

import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.ItemCartRepository;
import com.example.quitandafrescor.repository.ProductRepository;

@RestController
@RequestMapping("itemCart")
public class ItemCartController {

    private ProductRepository productRepository;

    private ItemCartRepository itemCartRepository;

    public ItemCartController(ProductRepository productRepository, ItemCartRepository itemCartRepository) {
        this.productRepository = productRepository;
        this.itemCartRepository = itemCartRepository;

    }

    private List<ItemCart> itemCart = new ArrayList<>();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/addCart/{id}")
    public ResponseEntity<Void> addCart(@PathVariable Long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (prod.isPresent()) {
            Product product = prod.get();

            // Verifica se o produto já está no carrinho
            Optional<ItemCart> existingItem = itemCart.stream()
                    .filter(item -> item.getProduct().getId().equals(product.getId()))
                    .findFirst();

            if (existingItem.isPresent()) {
                // Se o produto já está no carrinho, apenas incrementa a quantidade
                ItemCart item = existingItem.get();
                item.setQuantity(item.getQuantity() + 1);
                item.setSubTotalValue(item.getProductValue() * item.getQuantity());
            } else {
                // Se o produto não está no carrinho, adiciona um novo item
                ItemCart item = new ItemCart();
                item.setProduct(product);
                item.setProductValue(product.getValue());
                item.setQuantity(1);
                item.setSubTotalValue(item.getProductValue() * item.getQuantity());
                itemCart.add(item);
            }

            itemCartRepository.saveAll(itemCart);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCartResponseDTO> getCartById(@PathVariable Long id) {
        Optional<ItemCart> itemOpt = itemCartRepository.findById(id);
        if (itemOpt.isPresent()) {
            ItemCart item = itemOpt.get();
            Product product = item.getProduct();
            ItemCartResponseDTO response = new ItemCartResponseDTO(item, product);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCartUpdateDTOReturn> updateItemCartQuantity(@PathVariable Long id,
            @RequestBody ItemCartRequestDTO request) {
        Optional<ItemCart> itemOpt = itemCartRepository.findById(id);
        if (itemOpt.isPresent()) {
            ItemCart item = itemOpt.get();
            item.setQuantity(request.quantity());
            item.setSubTotalValue(item.getProductValue() * item.getQuantity());
            itemCartRepository.save(item);
            Product product = item.getProduct();
            ItemCartUpdateDTOReturn response = new ItemCartUpdateDTOReturn(product, item);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemCart(@PathVariable Long id) {
        Optional<ItemCart> itemOpt = itemCartRepository.findById(id);
        if (itemOpt.isPresent()) {
            itemCartRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.example.quitandafrescor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.quitandafrescor.model.ItemCart;

import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.ItemCartRepository;
import com.example.quitandafrescor.repository.ProductRepository;

@RestController
@RequestMapping("cart")
public class CartController {

    private ProductRepository productRepository;

    private ItemCartRepository itemCartRepository;

    public CartController(ProductRepository productRepository, ItemCartRepository itemCartRepository) {
        this.productRepository = productRepository;
        this.itemCartRepository = itemCartRepository;

    }

    private List<ItemCart> itemCart = new ArrayList<>();

    @GetMapping("/addCart/{id}")
    public ResponseEntity<Void> addCart(@PathVariable Long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (prod.isPresent()) {
            Product product = prod.get();
            ItemCart item = new ItemCart();
            item.setProduct(product);
            item.setProductValue(product.getValue());
            item.setQuantity(0); // Inicializa a quantidade com 0
            item.setQuantity(item.getQuantity() + 1);
            item.setSubTotalValue(item.getProductValue() * item.getQuantity());
            itemCart.add(item);
            itemCartRepository.saveAll(itemCart);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.example.quitandafrescor.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.example.quitandafrescor.product.ProductRequestDTO;
import com.example.quitandafrescor.product.ProductResponseDTO;

import com.example.quitandafrescor.product.Product;
import com.example.quitandafrescor.product.ProductRepository;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDTO data) {
        Product productData = new Product(data);

        repository.save(productData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProductResponseDTO> getAll() {
        List<ProductResponseDTO> productList = repository.findAll().stream().map(ProductResponseDTO::new).toList();
        return productList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
            repository.delete(optionalProduct.get());
    }

}

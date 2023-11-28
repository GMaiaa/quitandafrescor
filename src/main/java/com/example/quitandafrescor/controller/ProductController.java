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

import java.util.List;
import java.util.Map;

import com.example.quitandafrescor.dto.ProductRequestDTO;
import com.example.quitandafrescor.dto.ProductResponseDTO;
import com.example.quitandafrescor.dto.ProductUpdateDTO;
import com.example.quitandafrescor.dto.ProductUpdateDTOReturn;
import com.example.quitandafrescor.service.IProductService;

@RestController
@RequestMapping("product")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/relatedProducts/{category}")
    public List<ProductResponseDTO> getRelatedProducts(@PathVariable String category) {
        return productService.getRelatedProducts(category);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody ProductRequestDTO data) {
        return productService.saveProduct(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return productService.getAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ProductUpdateDTOReturn> updateProduct(@PathVariable Long id,
            @RequestBody ProductUpdateDTO data) {
        return productService.updateProduct(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/findByName")
    public List<ProductResponseDTO> findProductByName(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        return productService.findProductByName(name);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/orderedByAmount")
    public List<ProductResponseDTO> getAllOrderedByAmount() {
        return productService.getAllOrderedByAmount();
    }
}
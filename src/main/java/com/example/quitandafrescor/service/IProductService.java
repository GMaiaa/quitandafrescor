package com.example.quitandafrescor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.quitandafrescor.dto.ProductRequestDTO;
import com.example.quitandafrescor.dto.ProductResponseDTO;
import com.example.quitandafrescor.dto.ProductUpdateDTO;
import com.example.quitandafrescor.dto.ProductUpdateDTOReturn;

public interface IProductService {
    List<ProductResponseDTO> getRelatedProducts(String category);

    ResponseEntity<String> saveProduct(ProductRequestDTO data);

    List<ProductResponseDTO> getAll();

    ResponseEntity<ProductResponseDTO> getProductById(Long id);

    ResponseEntity<Void> deleteProduct(Long id);

    ResponseEntity<ProductUpdateDTOReturn> updateProduct(Long id, ProductUpdateDTO data);

     List<ProductResponseDTO> findProductByName(String name);
}

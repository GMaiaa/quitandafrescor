package com.example.quitandafrescor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quitandafrescor.dto.ProductRequestDTO;
import com.example.quitandafrescor.dto.ProductResponseDTO;
import com.example.quitandafrescor.dto.ProductUpdateDTO;
import com.example.quitandafrescor.dto.ProductUpdateDTOReturn;
import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.ItemCart;
import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.CartRepository;
import com.example.quitandafrescor.repository.ItemCartRepository;
import com.example.quitandafrescor.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService implements IProductService {

    private ProductRepository productRepository;

    private ItemCartRepository itemCartRepository;

    private CartRepository cartRepository;

    public ProductService(ProductRepository productRepository, ItemCartRepository itemCartRepository,
            CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.itemCartRepository = itemCartRepository;
        this.cartRepository = cartRepository;
    }

    public List<ProductResponseDTO> getRelatedProducts(String category) {
        return productRepository.findByCategory(category).stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public ResponseEntity<String> saveProduct(ProductRequestDTO data) {
        Optional<Product> existingProductWithName = productRepository.findByNameIgnoreCase(data.name());
        Optional<Product> existingProductWithImage = productRepository.findByImage(data.image());
        if (existingProductWithName.isPresent()) {
            return new ResponseEntity<>("Product with the same name already exists", HttpStatus.BAD_REQUEST);
        } else if (existingProductWithImage.isPresent()) {
            return new ResponseEntity<>("Product with the same image URL already exists", HttpStatus.BAD_REQUEST);
        } else {
            Product productData = new Product(data);
            productRepository.save(productData);
            return new ResponseEntity<>("Product saved successfully", HttpStatus.CREATED);
        }
    }

    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream().map(ProductResponseDTO::new)
                .toList();
    }

    public ResponseEntity<ProductResponseDTO> getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductResponseDTO productResponseDTO = new ProductResponseDTO(product);
            return ResponseEntity.ok(productResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Buscar todos os ItemCart que contêm o produto
            List<ItemCart> items = itemCartRepository.findAllByProduct(product);

            // Remover o produto de todos os ItemCart
            for (ItemCart item : items) {
                // Atualizar o valor total do carrinho
                Cart cart = item.getCart();
                cart.setTotalValue(cart.getTotalValue() - item.getSubTotalValue());
                cartRepository.save(cart);

                // Deletar o ItemCart
                itemCartRepository.delete(item);
            }

            // Agora você pode deletar o produto
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<ProductUpdateDTOReturn> updateProduct(Long id, ProductUpdateDTO data) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.updateProduct(data);
            productRepository.save(product);
            return ResponseEntity.ok(new ProductUpdateDTOReturn(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
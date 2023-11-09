package com.example.quitandafrescor.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.quitandafrescor.dto.ItemCartRequestDTO;
import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.ItemCart;
import com.example.quitandafrescor.model.Order;
import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.CartRepository;
import com.example.quitandafrescor.repository.OrderRepository;
import com.example.quitandafrescor.repository.ProductRepository;


@RestController
@RequestMapping("cart")
public class CartController {

    private CartRepository cartRepository;

    private OrderRepository orderRepository;

    private ProductRepository productRepository;


    public CartController(CartRepository cartRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

    }

    private List<ItemCart> itemCart = new ArrayList<ItemCart>();


    @GetMapping("/addCart/{id}")
    public void addCard(@PathVariable Long id){
        Optional<Product> prod = productRepository.findById(id);
        Product product = prod.get();
        ItemCart item = new ItemCart();
        item.setProduct(product);
        item.setProductValue(product.getValue());
        item.setQuantity(item.getQuantity()+1);
        itemCart.add(item);

    }

}

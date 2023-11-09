package com.example.quitandafrescor.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.example.quitandafrescor.dto.CartRequestDTO;
import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.ItemCart;
import com.example.quitandafrescor.model.Order;
import com.example.quitandafrescor.repository.CartRepository;
import com.example.quitandafrescor.repository.OrderRepository;

@RestController
@RequestMapping("cart")
public class CartController {

    private CartRepository cartRepository;

    private OrderRepository orderRepository;

    public CartController(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveCart(@RequestBody Cart cart) {
        cartRepository.save(cart);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);

        if (optionalCart.isPresent()) {
            return ResponseEntity.ok(optionalCart.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            cartRepository.delete(optionalCart.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<Void> checkout(@PathVariable Long id) {
    Optional<Cart> optionalCart = cartRepository.findById(id);
    if (optionalCart.isPresent()) {
        Cart cart = optionalCart.get();

        // Cria um novo pedido a partir do carrinho
        Order order = new Order();
        order.setCart(cart);
        // Salva o pedido
        orderRepository.save(order);

        // Exclui o carrinho
        cartRepository.delete(cart);

        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}

@PostMapping("/{id}/addItem")
public ResponseEntity<Void> addItemToCart(@PathVariable Long id, @RequestBody CartRequestDTO cartRequest) {
    Optional<Cart> optionalCart = cartRepository.findById(id);
    if (optionalCart.isPresent()) {
        Cart cart = optionalCart.get();

        // Cria um novo ItemCart a partir do CartRequestDTO
        ItemCart item = new ItemCart();
        item.setProduct(cartRequest.product());
        item.setQuantity(cartRequest.quantity());
        item.setCart(cart);

        // Adiciona o item ao carrinho
        cart.getItens().add(item);

        // Salva o carrinho
        cartRepository.save(cart);

        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}


    // Atualizar o carrinho pode ser complexo dependendo da lógica do seu negócio.
    // Você pode precisar adicionar ou remover itens, então você pode precisar de um método separado para isso.
}
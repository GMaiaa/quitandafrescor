package com.example.quitandafrescor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quitandafrescor.model.Cart;
import com.example.quitandafrescor.model.Order;
import com.example.quitandafrescor.repository.CartRepository;
import com.example.quitandafrescor.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;

    private CartRepository cartRepository;

    public OrderController(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @PostMapping("/confirmPurchase")
    public ResponseEntity<Void> confirmPurchase(@RequestParam String client, @RequestParam String cpf,
            @RequestParam String email, @RequestParam Integer phoneNumber, @RequestParam String paymentMethod,
            @RequestParam Integer cep, @RequestParam Integer adressNumber, @RequestParam Float moneyChange) {
        // Busca o carrinho de compras do repositório
        List<Cart> carts = cartRepository.findAll();

        // Se não houver nenhum carrinho ou o último carrinho já estiver associado a um
        // pedido, retorna um erro
        if (carts.isEmpty() || carts.get(carts.size() - 1).getOrder() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            Cart cart = carts.get(carts.size() - 1);

            // Cria um novo pedido e associa o carrinho a ele
            Order order = new Order();
            order.setClient(client);
            order.setCpf(cpf);
            order.setEmail(email);
            order.setPhoneNumber(phoneNumber);
            order.setPaymentMethod(paymentMethod);
            order.setCep(cep);
            order.setAdressNumber(adressNumber);
            order.setMoneyChange(moneyChange);
            order.setCart(cart);
            orderRepository.save(order);

            // Limpa o carrinho
            cart.getItens().clear();
            cart.setTotalValue(0f);
            cartRepository.save(cart);

            return ResponseEntity.ok().build();
        }
    }

}

package com.example.quitandafrescor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

        @Autowired
        ProductRepository productRepository;

        @Bean
        public CommandLineRunner initProductDatabase() {
                return args -> {
                        List<Product> products = new ArrayList<>();

                        products.add(
                                        new Product(1L, "Apple", "Red, juicy apple", 1.99f,
                                                        "https://example.com/apple.png", 10, "Fruits"));
                        products.add(new Product(2L, "Orange", "Yellow, citrus fruit", 2.99f,
                                        "https://example.com/orange.png", 20,
                                        "Fruits"));
                        products.add(new Product(3L, "Banana", "Yellow, curved fruit", 0.99f,
                                        "https://example.com/banana.png", 30,
                                        "Fruits"));
                        products.add(
                                        new Product(4L, "Potato", "Brown, starchy vegetable", 0.49f,
                                                        "https://example.com/potato.png", 40,
                                                        "Vegetables"));
                        products.add(new Product(5L, "Tomato", "Red, juicy fruit", 0.59f,
                                        "https://example.com/tomato.png", 50,
                                        "Vegetables"));

                        productRepository.saveAll(products);
                };
        }
}

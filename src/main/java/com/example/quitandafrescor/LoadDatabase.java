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
                                        new Product(1L, "Maçã", "Red, juicy apple", 1.99f,
                                                        "https://th.bing.com/th/id/OIP.ibRIn_Z0vVlHIHl7lxKgHgHaHa?w=179&h=180&c=7&r=0&o=5&pid=1.7", 10, "Fruta"));
                        products.add(new Product(2L, "Laranja", "Yellow, citrus fruit", 2.99f,
                                        "https://th.bing.com/th/id/OIP.jZAEWAGZ2bYcW-V14o6CCwHaFj?w=265&h=199&c=7&r=0&o=5&pid=1.7", 20,
                                        "Fruta"));
                        products.add(new Product(3L, "Banana", "Yellow, curved fruit", 0.99f,
                                        "https://th.bing.com/th/id/OIP.EGrXAOdEYSzNrjbZiFP03wHaFj?w=264&h=198&c=7&r=0&o=5&pid=1.7", 30,
                                        "Fruta"));
                        products.add(
                                        new Product(4L, "Batata", "Brown, starchy vegetable", 0.49f,
                                                        "https://th.bing.com/th/id/OIP.3UyCi2UAGC4JXav5TF2lHgHaFd?w=241&h=180&c=7&r=0&o=5&pid=1.7", 40,
                                                        "Verduras"));
                        products.add(new Product(5L, "Tomate", "Red, juicy fruit", 0.59f,
                                        "https://th.bing.com/th/id/OIP.Ce5ZcF05xSIfeaCmGdklfwHaGm?w=209&h=187&c=7&r=0&o=5&pid=1.7", 50,
                                        "Verdura"));

                        productRepository.saveAll(products);
                };
        }
}

package com.example.quitandafrescor;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

        private static final String FRUITS = "Fruta";

        @Bean
        public CommandLineRunner initProductDatabase(ProductRepository productRepository) {
                return args -> {
                        List<Product> products = new ArrayList<>();

                        products.add(
                                        new Product(1L, "Maçã", "Red, juicy apple", 1.99f,
                                                        "https://scfoods.fbitsstatic.net/img/p/maca-red-argentina-unidade-70942/257561.jpg?w=800&h=800&v=no-change&qs=ignore", 10, FRUITS));
                        products.add(new Product(2L, "Laranja", "Yellow, citrus fruit", 2.99f,
                                        "https://www.proativaalimentos.com.br/image/cache/catalog/img_prod/Laranja_lima_600x600[1]-1000x1000.jpg", 20,
                                        FRUITS));
                        products.add(new Product(3L, "Banana", "Yellow, curved fruit", 0.99f,
                                        "https://frutasbrasilsul.com.br/wp-content/uploads/banana-nanica.png", 30,
                                        FRUITS));
                        products.add(
                                        new Product(4L, "Batata", "Brown, starchy vegetable", 0.49f,
                                                        "https://mercadoorganico.com/6428-large_default/batata-inglesa-organica-500g-osm.jpg", 40,
                                                        "Verduras"));
                        products.add(new Product(5L, "Tomate", "Red, juicy fruit", 0.59f,
                                        "https://scfoods.fbitsstatic.net/img/p/tomate-debora-maduro-para-molho-500g-70892/257510.jpg?w=800&h=800&v=no-change&qs=ignore", 50,
                                        "Verdura"));

                        productRepository.saveAll(products);
                };
        }
}

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
    private static final String GREENERYS = "Verdura";
    private static final String VEGETABLES = "Legume";
    private static final String DRINKS = "Bebida";
    private static final String NON_FOODS = "Não alimentar";

    @Bean
    public CommandLineRunner initProductDatabase(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                List<Product> products = new ArrayList<>();

                // Frutas
                products.add(new Product(1L, "Maçã", "Maçã", 1.99f,
                        "https://scfoods.fbitsstatic.net/img/p/maca-red-argentina-unidade-70942/257561.jpg?w=800&h=800&v=no-change&qs=ignore",
                        150, FRUITS));
                // Adicione mais frutas aqui...

                // Verduras
                products.add(new Product(31L, "Alface Crespa", "Alface Crespa", 0.99f,
                        "https://www.aboaterra.com.br/wp-content/uploads/2022/07/Alface-Crespa-Organico.jpg", 200,
                        GREENERYS));
                // Adicione mais verduras aqui...

                // Legumes
                products.add(new Product(61L, "Cenoura", "Cenoura", 0.89f,
                        "https://carrefourbrfood.vtexassets.com/arquivos/ids/12966487/cenoura-com-folhas-carrefour-400g-1.jpg?v=637479789303930000",
                        250, VEGETABLES));
                // Adicione mais legumes aqui...

                // Bebidas
                products.add(new Product(91L, "Suco Maguary", "Suco de laranja Maguary 1L", 2.99f,
                        "https://m.media-amazon.com/images/I/81HtFrrCyWL._AC_UF1000,1000_QL80_.jpg", 80, DRINKS));
                // Adicione mais bebidas aqui...

                // Não alimentar
                products.add(new Product(121L, "Bombril", "Bombril ", 0.99f,
                        "https://gruposoares.vtexassets.com/arquivos/ids/171511/Esponja-de-Aco-Bombril-com-8-unidades.jpg?v=638204484766530000",
                        100, NON_FOODS));
                // Adicione mais produtos não alimentares aqui...

                productRepository.saveAll(products);
            }
        };
    }
}
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
                products.add(new Product(21L, "Alface Crespa", "Alface Crespa", 0.99f,
                        "https://www.aboaterra.com.br/wp-content/uploads/2022/07/Alface-Crespa-Organico.jpg", 200,
                        GREENERYS));
                // Adicione mais verduras aqui...

                // Legumes
                products.add(new Product(41L, "Cenoura", "Cenoura", 0.89f,
                        "https://carrefourbrfood.vtexassets.com/arquivos/ids/12966487/cenoura-com-folhas-carrefour-400g-1.jpg?v=637479789303930000",
                        250, VEGETABLES));
                // Adicione mais legumes aqui...

                // Bebidas
                products.add(new Product(61L, "Suco Maguary", "Suco de laranja Maguary 1L", 2.99f,
                        "https://m.media-amazon.com/images/I/81HtFrrCyWL._AC_UF1000,1000_QL80_.jpg", 80, DRINKS));
                // Adicione mais bebidas aqui...

                // Não alimentar
                products.add(new Product(81L, "Bombril", "Bombril ", 4.99f,
                        "https://gruposoares.vtexassets.com/arquivos/ids/171511/Esponja-de-Aco-Bombril-com-8-unidades.jpg?v=638204484766530000",
                        100, NON_FOODS));
                
                products.add(new Product(82L, "Velas", "Velas ", 9.99f,
                        "https://http2.mlstatic.com/D_NQ_NP_666216-MLB41729025227_052020-F.jpg",
                        100, NON_FOODS));
                
                products.add(new Product(83L, "Papel higiênico", "Papel higiênico ", 16.99f,
                        "https://a-static.mlcdn.com.br/1500x1500/papel-higienico-fofinho-30m-folha-dupla-4-rolos-cia-canoinhas/costaatacado/1026843/22c688c099e8966bb7059306ab54049f.jpg",
                        100, NON_FOODS));

                products.add(new Product(84L, "Sabonete", "Sabonete ", 14.99f,
                        "https://th.bing.com/th/id/OIP.2oaKhyU_s8kyF93bGuLspQHaHa?w=213&h=213&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(85L, "Shampoo", "Shampo ", 10.99f,
                        "https://th.bing.com/th/id/OIP.foL6Jl6YmuVTN2fEmo1NrwHaIO?w=170&h=189&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(86L, "Condicionador", "Condicionador ", 10.99f,
                        "https://th.bing.com/th/id/OIP.3UkGO6SjgwsxGSRf_JHcHAHaHa?w=205&h=205&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(87L, "Perfume", "Perfume ", 19.99f,
                        "https://th.bing.com/th/id/OIP.BkPC7aBj4XDcYLywAz9jeQHaKS?w=153&h=212&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(88L, "Escova de dente", "Escova de dente", 14.99f,
                        "https://th.bing.com/th/id/OIP.sifJiW2xzmk7Xi_kr3R1yAHaHa?w=205&h=204&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(89L, "Pasta de dente", "Pasta de dente", 7.99f,
                        "https://th.bing.com/th/id/OIP.PnT5fpKSmzSRfMduxO2f8AHaHa?w=182&h=182&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(810L, "Caixa de Fósforos", "Fósforo ", 6.99f,
                        "https://th.bing.com/th/id/OIP.MOG5YUK7DgIwk1w4mVLJIQHaFj?rs=1&pid=ImgDetMain",
                        100, NON_FOODS));

                products.add(new Product(811L, "Sacos de lixo", "Sacos de lixo ", 6.98f,
                        "https://th.bing.com/th/id/OIP.JL1iWicWndQ3FSOv6m-QyQHaHa?w=213&h=213&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(812L, "Esponjas de lavar louça", "Esponjas de lavar louça ", 7.06f,
                        "https://th.bing.com/th/id/OIP.RA7fCH7UB6BuKFMPBB-1GwHaD4?w=319&h=180&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(813L, "Detergente", "Detergente ", 1.99f,
                        "https://a-static.mlcdn.com.br/450x450/detergente-liquido-ype-lava-loucas-neutro-500ml-embalagem-com-24-unidades/efacil/1709980/e49824e40fa56aabae218762bbff73c8.jpeg",
                        100, NON_FOODS));

                products.add(new Product(814L, "Desinfetante", "Desinfetante ", 11.50f,
                        "https://th.bing.com/th/id/OIP.lbS_u0OwDwHOG5Z4W5s14AHaJo?w=147&h=191&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(815L, "Álcool em gel", "Álcool em gel ", 5.99f,
                        "https://th.bing.com/th/id/OIP.xRIlUEEMG0jkuI1n_nHY3wHaHa?w=209&h=209&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(816L, "Ração para animais", "Ração para animais ", 29.99f,
                        "https://th.bing.com/th/id/OIP.mz4BRK-6abVVv1wjuFrXqwHaHa?w=197&h=197&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(817L, "Brinquedos para animais", "Brinquedos para animais ", 3.99f,
                        "https://th.bing.com/th/id/OIP.x1WLRSrGUkxi79AHL1DF8AAAAA?w=182&h=182&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(818L, "Incensos", "Incensos ", 2.79f,
                        "https://th.bing.com/th/id/OIP.3GUofzRZEiRXw_lQ837WsQHaFz?w=237&h=186&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));

                products.add(new Product(819L, "Vassoura", "Vassoura ", 6.21f,
                        "https://th.bing.com/th/id/OIP.fALTMUq7RGTL6TalOQGpMAHaHa?w=216&h=216&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));
                products.add(new Product(820L, "Água Sanitaria", "Água Sanitaria ", 13.21f,
                        "https://th.bing.com/th/id/OIP.rho_9tbFORcxgJuy9KvWNQHaHa?w=201&h=202&c=7&r=0&o=5&pid=1.7",
                        100, NON_FOODS));
                // Adicione mais produtos não alimentares aqui...

                productRepository.saveAll(products);
            }
        };
    }
}
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
                                products.add(new Product(2L, "Uva Verde", "Uva verde sem semente 1 caixa", 4.00f,
                                                "https://img.irroba.com.br/fit-in/600x600/filters:fill(transparent):quality(80)/shoeboxs/catalog/80039.png",
                                                150, FRUITS));
                                products.add(new Product(3L, "Banana", "Banana Prata", 1.00f,
                                                "https://acdn.mitiendanube.com/stores/001/254/392/products/frutas_hortifruti_hortifit_delivery_banana-prata_211-8db6d3e5f3613fb8be16922801932891-1024-1024.jpg",
                                                150, FRUITS));
                                products.add(new Product(4L, "Laranja Lima", "Laranja Lima", 1.50f,
                                                "https://www.proativaalimentos.com.br/image/cache/catalog/img_prod/Laranja_lima_600x600[1]-1000x1000.jpg",
                                                150, FRUITS));
                                products.add(new Product(5L, "Morango", "Morango 1 caixa", 4.00f,
                                                "https://cdn.shoppub.io/cdn-cgi/image/w=1000,h=1000,q=80,f=auto/cenourao/media/uploads/produtos/foto/nhkgvszz/morango.jpg",
                                                150, FRUITS));
                                products.add(new Product(6L, "Abacaxi", "Abacaxi", 4.99f,
                                                "https://naturaldaterra.com.br/media/catalog/product/1/3/137479---2080540000003---abacaxi-org-kg.jpg?auto=webp&format=pjpg&width=640&height=800&fit=cover",
                                                150, FRUITS));
                                products.add(new Product(7L, "Mamão Papaia", "Mamão Papaia", 4.50f,
                                                "https://loja.doiscunhados.com.br/wp-content/uploads/2020/03/mamao-papaia.jpg",
                                                150, FRUITS));
                                products.add(new Product(8L, "Pêra Williams", "Pêra Williams", 2.00f,
                                                "https://static.wixstatic.com/media/d40923_842e71d1f11b4ca19f2701039dac4ed3~mv2.jpg/v1/crop/x_46,y_40,w_655,h_689/fill/w_380,h_402,al_c,q_80,usm_0.66_1.00_0.01,enc_auto/d40923_842e71d1f11b4ca19f2701039dac4ed3~mv2.jpg",
                                                150, FRUITS));
                                products.add(new Product(9L, "Manga", "Manga", 4.99f,
                                                "https://www.quitandatomio.com.br/upload/1085343213-beneficios-da-manga-palmer-para-a-saude.jpg",
                                                150, FRUITS));
                                products.add(new Product(10L, "Melancia", "Melancia", 7.99f,
                                                "https://revistacampoenegocios.com.br/wp-content/uploads/2020/11/Melancia-01.jpg",
                                                150, FRUITS));
                                products.add(new Product(11L, "Kiwi", "Kiwi", 2.99f,
                                                "https://images.tcdn.com.br/img/img_prod/924009/kiwi_129_1_747cd5f775f304e08b3b2f0eabe55539.jpg",
                                                150, FRUITS));
                                products.add(new Product(12L, "Pêssego", "Pêssego", 2.99f,
                                                "https://hortifrutijardins.com.br/wp-content/uploads/2020/10/pessego-beneficios.jpg",
                                                150, FRUITS));
                                products.add(new Product(13L, "Abacate", "Abacate", 4.99f,
                                                "https://acdn.mitiendanube.com/stores/746/397/products/abacate21-1070ab6091f64b1fe415220054831312-640-0.jpg",
                                                150, FRUITS));
                                products.add(new Product(14L, "Caju", "Caju", 2.50f,
                                                "https://img.irroba.com.br/fit-in/600x600/filters:fill(transparent):quality(80)/shoeboxs/catalog/80039.png",
                                                150, FRUITS));
                                products.add(new Product(15L, "Limão Taiti", "Limão Taiti", 1.00f,
                                                "https://static.significados.com.br/foto/limao-taiti-cke.jpg",
                                                150, FRUITS));
                                products.add(new Product(16L, "Limão Siciliano", "Limão Siciliano", 2.50f,
                                                "https://global.cdn.magazord.com.br/vasoeflor/img/2022/07/produto/1074/muda-limao-siciliano-90cm.jpg?ims=fit-in/800x800/filters:fill(white)",
                                                150, FRUITS));
                                products.add(new Product(17L, "Maracujá", "Maracujá", 3.50f,
                                                "https://camposverde.ao/wp-content/uploads/2023/01/maracuja.webp",
                                                150, FRUITS));
                                products.add(new Product(18L, "Melão", "Melão", 4.99f,
                                                "https://www.proativaalimentos.com.br/image/cache/catalog/img_prod/melaoamarelo1_502698640_jpg_1024x1024[1]-1000x1000.jpg",
                                                150, FRUITS));
                                products.add(new Product(19L, "Pitaya", "Pitaya", 6.50f,
                                                "https://boa.vtexassets.com/unsafe/fit-in/720x720/center/middle/https%3A%2F%2Fboa.vtexassets.com%2Farquivos%2Fids%2F354195%2FPitaya-Selecionada-kg.jpg%3Fv%3D638219250456330000",
                                                150, FRUITS));
                                products.add(new Product(20L, "Goiaba", "Goiaba", 3.00f,
                                                "https://savegnagoio.vtexassets.com/arquivos/ids/353920-800-450?v=638080435664030000&width=800&height=450&aspect=true",
                                                150, FRUITS));

                                // Adicione mais frutas aqui...

                                // Verduras
                                products.add(new Product(21L, "Alface Crespa", "Alface Crespa", 0.99f,
                                                "https://www.aboaterra.com.br/wp-content/uploads/2022/07/Alface-Crespa-Organico.jpg",
                                                200,
                                                GREENERYS));
                                // Adicione mais verduras aqui...

                                // Legumes
                                products.add(new Product(41L, "Cenoura", "Cenoura", 0.89f,
                                                "https://carrefourbrfood.vtexassets.com/arquivos/ids/12966487/cenoura-com-folhas-carrefour-400g-1.jpg?v=637479789303930000",
                                                250, VEGETABLES));
                                // Adicione mais legumes aqui...

                                // Bebidas
                                products.add(new Product(61L, "Suco Maguary", "Suco de laranja Maguary 1L", 2.99f,
                                                "https://m.media-amazon.com/images/I/81HtFrrCyWL._AC_UF1000,1000_QL80_.jpg",
                                                80, DRINKS));
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

                                products.add(new Product(90L, "Caixa de Fósforos", "Fósforo ", 6.99f,
                                                "https://th.bing.com/th/id/OIP.MOG5YUK7DgIwk1w4mVLJIQHaFj?rs=1&pid=ImgDetMain",
                                                100, NON_FOODS));

                                products.add(new Product(91L, "Sacos de lixo", "Sacos de lixo ", 6.98f,
                                                "https://th.bing.com/th/id/OIP.JL1iWicWndQ3FSOv6m-QyQHaHa?w=213&h=213&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));

                                products.add(new Product(92L, "Esponjas de lavar louça", "Esponjas de lavar louça ",
                                                7.06f,
                                                "https://th.bing.com/th/id/OIP.RA7fCH7UB6BuKFMPBB-1GwHaD4?w=319&h=180&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));

                                products.add(new Product(93L, "Detergente", "Detergente ", 1.99f,
                                                "https://a-static.mlcdn.com.br/450x450/detergente-liquido-ype-lava-loucas-neutro-500ml-embalagem-com-24-unidades/efacil/1709980/e49824e40fa56aabae218762bbff73c8.jpeg",
                                                100, NON_FOODS));

                                products.add(new Product(94L, "Desinfetante", "Desinfetante ", 11.50f,
                                                "https://th.bing.com/th/id/OIP.lbS_u0OwDwHOG5Z4W5s14AHaJo?w=147&h=191&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));

                                products.add(new Product(95L, "Álcool em gel", "Álcool em gel ", 5.99f,
                                                "https://th.bing.com/th/id/OIP.xRIlUEEMG0jkuI1n_nHY3wHaHa?w=209&h=209&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));

                                products.add(new Product(96L, "Ração para animais", "Ração para animais ", 29.99f,
                                                "https://th.bing.com/th/id/OIP.mz4BRK-6abVVv1wjuFrXqwHaHa?w=197&h=197&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));

                                products.add(new Product(97L, "Brinquedos para animais", "Brinquedos para animais ",
                                                3.99f,
                                                "https://th.bing.com/th/id/OIP.x1WLRSrGUkxi79AHL1DF8AAAAA?w=182&h=182&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));

                                products.add(new Product(98L, "Incensos", "Incensos ", 2.79f,
                                                "https://th.bing.com/th/id/OIP.3GUofzRZEiRXw_lQ837WsQHaFz?w=237&h=186&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));

                                products.add(new Product(99L, "Vassoura", "Vassoura ", 6.21f,
                                                "https://th.bing.com/th/id/OIP.fALTMUq7RGTL6TalOQGpMAHaHa?w=216&h=216&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));
                                products.add(new Product(100L, "Água Sanitaria", "Água Sanitaria ", 13.21f,
                                                "https://th.bing.com/th/id/OIP.rho_9tbFORcxgJuy9KvWNQHaHa?w=201&h=202&c=7&r=0&o=5&pid=1.7",
                                                100, NON_FOODS));

                                productRepository.saveAll(products);
                        }
                };
        }
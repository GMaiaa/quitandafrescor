package com.example.quitandafrescor.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Entity(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Float value;
    private String image;
    private Integer amount;
    private String category;

    public Product(ProductRequestDTO data){
        this.name = data.name();
        this.description = data.description();
        this.value = data.value();
        this.image = data.image();
        this.amount = data.amount();
        this.category = data.category();

    }

    
}

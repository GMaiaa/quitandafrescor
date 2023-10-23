package com.example.quitandafrescor.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Table(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "\"value\"", nullable = false)
    private Float value;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "category", nullable = false)
    private String category;

    public Product(ProductRequestDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.value = data.value();
        this.image = data.image();
        this.amount = data.amount();
        this.category = data.category();

    }

}

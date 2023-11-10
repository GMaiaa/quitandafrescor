package com.example.quitandafrescor.model;

import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "orders")
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client", nullable = false)
    private String client;

    @CPF
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cep", nullable = true)
    private Integer cep;

    @Column(name = "adressNumber", nullable = true)
    private Integer adressNumber;

    @Column(name = "phoneNumber", nullable = false)
    private Integer phoneNumber;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "moneyChange", nullable = true)
    private Float moneyChange;

    @OneToOne
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;

    @Column(name = "orderDate", nullable = false)
    private Date orderDate = new Date();

}
package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartProductEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    private CartEntity cart;

    @OneToOne
    private ProductEntity productEntity;
}

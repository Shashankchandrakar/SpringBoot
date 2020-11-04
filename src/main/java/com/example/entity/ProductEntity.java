package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Product")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String colour;
    private Double price;
}

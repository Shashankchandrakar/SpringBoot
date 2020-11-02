package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class Cart {

    private int cartId;
    private List<Product> product;
}

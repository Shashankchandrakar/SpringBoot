package com.example.dto;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String name;
    private String userRole;
    private Cart cart;
}

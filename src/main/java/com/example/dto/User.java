package com.example.dto;

import com.example.entity.UserRole;
import lombok.Data;

@Data
public class User {

    private Integer id;
    private String name;
    private UserRole userRole;
    private Cart cart;
}

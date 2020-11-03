package com.example.repository;

import com.example.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductEntity extends CrudRepository<CartProductEntity, Integer> {
}

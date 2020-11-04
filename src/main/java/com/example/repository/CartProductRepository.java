package com.example.repository;

import com.example.entity.CartEntity;
import com.example.entity.CartProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends CrudRepository<CartProductEntity, Integer> {
}

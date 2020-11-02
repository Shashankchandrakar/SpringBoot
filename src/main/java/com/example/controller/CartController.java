package com.example.controller;

import com.example.dto.Cart;
import com.example.dto.Product;
import com.example.entity.CartEntity;
import com.example.entity.CartProductEntity;
import com.example.entity.ProductEntity;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class CartController {

    private final UserRepository userRepository;

    @Autowired
    public CartController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/cartPage")
    public Cart getCartData(@RequestParam(value = "userId") int userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (!userEntityOptional.isPresent()) {
            return null;
        }
        UserEntity userEntity = userEntityOptional.get();
        CartEntity cartEntity = userEntity.getCartEntity();

        List<Product> productList = new ArrayList<>();

        Cart cart = new Cart();
        for (CartProductEntity cartProductEntity : cartEntity.getProductEntityList()) {

            Product product = new Product();

            ProductEntity productEntity = cartProductEntity.getProductEntity();
            product.setId(productEntity.getId());
            product.setColour(productEntity.getColour());
            product.setName(productEntity.getName());

            productList.add(product);
        }

        cart.setCartId(cartEntity.getId());
        cart.setProduct(productList);
        return cart;
    }

}

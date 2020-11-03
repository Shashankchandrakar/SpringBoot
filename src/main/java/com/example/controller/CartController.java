package com.example.controller;

import com.example.dto.Cart;
import com.example.dto.Product;
import com.example.dto.UserProduct;
import com.example.entity.CartEntity;
import com.example.entity.CartProductEntity;
import com.example.entity.ProductEntity;
import com.example.entity.UserEntity;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class CartController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartController(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
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

    @PostMapping("/userCartDetails")
    public Product getProduct(@RequestBody UserProduct userProduct) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userProduct.getUserId());
        if(!optionalUserEntity.isPresent())
            return null;
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(userProduct.getProductId());
        if(!optionalProductEntity.isPresent())
            return null;

        CartEntity cartEntity = optionalUserEntity.get().getCartEntity();

        CartProductEntity cartProductEntity = new CartProductEntity();
        cartProductEntity.setCart(cartEntity);
        cartProductEntity.setProductEntity(optionalProductEntity.get());
        cartEntity.getProductEntityList().add(cartProductEntity);

        cartRepository.save(cartEntity);
        userRepository.save(optionalUserEntity.get());

        Product product = new Product();
        product.setId(optionalProductEntity.get().getId());
        product.setColour(optionalProductEntity.get().getColour());
        product.setName(optionalProductEntity.get().getName());

        return product;
    }


}

package com.example.controller;

import com.example.dto.Product;
import com.example.entity.ProductEntity;
import com.example.entity.UserEntity;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

import static com.example.entity.UserRole.ADMIN;

@RestController
public class AdminController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public AdminController(ProductRepository productRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/insertProductPage")
    public Product getProduct(@RequestParam(value = "id") Integer id, @RequestBody Product product) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

        if (optionalUserEntity.isPresent() &&
                Objects.equals(optionalUserEntity.get().getUserRole(), ADMIN)) {
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(product.getId());

            if (optionalProductEntity.isPresent()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setId(product.getId());
                productEntity.setColour(product.getColour());
                productEntity.setName(product.getName());
                productEntity.setPrice(product.getPrice());

                productRepository.save(productEntity);

                product.setId(productEntity.getId());

                return product;
            }
        }
        return null;
    }

    @PutMapping("/updateProductPage")
    public Product updateProductDetails(@RequestParam(value = "id") Integer id,
                                        @RequestBody Product product) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(product.getId());
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);

        if(!userEntityOptional.isPresent()){
            return null;
        }

        if(!userEntityOptional.get().getUserRole().equals(ADMIN)){
            return null;
        }

        if (!optionalProductEntity.isPresent()) {
            return null;
        }
        ProductEntity productEntity = optionalProductEntity.get();
        if (Objects.nonNull(product.getName())) {
            productEntity.setName(product.getName());
        }
        if (Objects.nonNull(product.getColour())) {
            productEntity.setColour(product.getColour());
        }

        if (Objects.nonNull(product.getPrice())) {
            productEntity.setPrice(product.getPrice());
        }

        productRepository.save(productEntity);

        return product;

    }

    @DeleteMapping("/deleteProduct")
    public void deleteProductDetails(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "id") Integer id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        System.out.println(userId);
        System.out.println(id);
        if (optionalUserEntity.isPresent()) {
            System.out.println(userId);
            if (Objects.equals(optionalUserEntity.get().getUserRole(), ADMIN)) {
                System.out.println(id);
                if (productRepository.existsById(id)) productRepository.deleteById(id);
            }
        }
    }
}
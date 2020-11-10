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

@RestController
public class AdminController {

    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public AdminController(ProductRepository productRepository, UserRepository userRepository){
        this.userRepository=userRepository;
        this.productRepository=productRepository;
    }

    @PostMapping("/insertProductPage")
    public Product getProduct(@RequestParam (value = "id") Integer id, @RequestBody Product product) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

        if(optionalUserEntity.isPresent() &&
                Objects.equals(optionalUserEntity.get().getUserRole(), "admin")){
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
            Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
            if(optionalUserEntity.isPresent()){

            }
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(product.getId());

             if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();
            productEntity.setName(product.getName());
            productEntity.setColour(product.getColour());
            productEntity.setPrice(product.getPrice());

            productRepository.save(productEntity);

            return product;
             }
             return null;
         }

     @DeleteMapping("/deleteProduct")
        public void deleteProductDetails(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(value = "id") Integer id) {
         Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
         System.out.println(userId);
         System.out.println(id);
         if (optionalUserEntity.isPresent()) {
             System.out.println(userId);
             if (Objects.equals(optionalUserEntity.get().getUserRole(), "admin")) {
                 System.out.println(id);
                 if (productRepository.existsById(id)) productRepository.deleteById(id);
             }
         }
         else
             return;
     }
}
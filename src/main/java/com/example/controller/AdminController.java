package com.example.controller;

import com.example.dto.Product;
import com.example.entity.ProductEntity;
import com.example.entity.UserEntity;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Optional;

@Controller
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

        if(optionalUserEntity.isPresent()) {

            String check = optionalUserEntity.get().getUserRole();
            if (Objects.equals(check, "admin")) {
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

    @DeleteMapping("/deleteProduct")
    public void deleteProductDetails(@RequestParam(value = "id") Integer id1, @RequestParam(value = "id") Integer id2) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id1);
        if(!optionalUserEntity.isPresent()){
            return;
        }
        else
          if (productRepository.existsById(id2)) productRepository.deleteById(id2);
    }

}

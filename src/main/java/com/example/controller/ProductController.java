package com.example.controller;

import com.example.dto.Product;
import com.example.entity.ProductEntity;
import com.example.repository.ProductRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/insertProductPage")
    public Product getProduct(@RequestBody Product product) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setColour(product.getColour());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());

        productRepository.save(productEntity);

        product.setId(productEntity.getId());

        return product;
    }

    @GetMapping("/forUserProductDetails")
    public List<Product> getAllProducts(){

        List<Product> productList = new ArrayList<>();

        for(ProductEntity productEntity: productRepository.findAll()){
            Product product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setColour(productEntity.getColour());
            product.setPrice(productEntity.getPrice());

            productList.add(product);

        }
        return  productList;

    }

    @GetMapping("/getProductDetails")
    public Product getProductDetails(@RequestParam(value = "id") Integer id) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);

        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();

            Product product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setColour(productEntity.getColour());
            product.setPrice(productEntity.getPrice());

            return product;
        }
        return null;
    }

    @PutMapping("/updateProductPage")
    public Product updateProductDetails(@RequestBody Product product) {

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
    public void deleteProductDetails(@RequestParam(value = "id") Integer id) {

        if (productRepository.existsById(id)) productRepository.deleteById(id);
    }
}

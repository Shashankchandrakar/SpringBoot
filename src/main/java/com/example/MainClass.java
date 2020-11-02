package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
@EntityScan(basePackages = "com.example")
public class MainClass {
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);  // format
    }
}

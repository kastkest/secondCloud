package com.flamexander.cloud.front.service;

import com.flamexander.cloud.common.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class FrontApp {

    @Autowired
    public RestTemplate restTemplate;


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontApp.class, args);
    }


    @GetMapping("/api/v1/products/front")
    public List<ProductDto> findAll() {
        return restTemplate.getForObject("http://product-service/api/v1/products", List.class);
    }

    @GetMapping
    public ProductDto findById(@PathVariable Long id) {
        return restTemplate.getForObject("http://product-service/api/v1/products/{id}/front", ProductDto.class);
    }

}

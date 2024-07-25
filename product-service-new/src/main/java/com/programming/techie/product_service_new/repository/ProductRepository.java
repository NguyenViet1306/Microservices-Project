package com.programming.techie.product_service_new.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.programming.techie.product_service_new.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}

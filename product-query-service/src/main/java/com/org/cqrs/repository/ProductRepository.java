package com.org.cqrs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.org.cqrs.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Long>
{
}

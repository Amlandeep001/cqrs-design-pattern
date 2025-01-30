package com.org.cqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cqrs.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{
}

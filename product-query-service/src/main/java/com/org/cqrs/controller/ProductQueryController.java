package com.org.cqrs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cqrs.entity.Product;
import com.org.cqrs.service.ProductQueryService;

@RestController
@RequestMapping("/products")
public class ProductQueryController
{
	private final ProductQueryService queryService;

	public ProductQueryController(ProductQueryService queryService)
	{
		this.queryService = queryService;
	}

	@GetMapping
	public List<Product> fetchAllProducts()
	{
		return queryService.getProducts();
	}
}

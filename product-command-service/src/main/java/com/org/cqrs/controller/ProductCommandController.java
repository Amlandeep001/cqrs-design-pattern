package com.org.cqrs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cqrs.dto.ProductEvent;
import com.org.cqrs.entity.Product;
import com.org.cqrs.service.ProductCommandService;

@RestController
@RequestMapping("/products")
public class ProductCommandController
{
	private final ProductCommandService commandService;

	public ProductCommandController(ProductCommandService commandService)
	{
		this.commandService = commandService;
	}

	@PostMapping
	public Product createProduct(@RequestBody ProductEvent productEvent)
	{
		return commandService.createProduct(productEvent);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable long id, @RequestBody ProductEvent productEvent)
	{
		return commandService.updateProduct(id, productEvent);
	}
}

package com.org.cqrs.service;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.org.cqrs.constant.EventType;
import com.org.cqrs.dto.ProductEvent;
import com.org.cqrs.entity.Product;
import com.org.cqrs.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductQueryService
{
	private final ProductRepository repository;

	public ProductQueryService(ProductRepository repository)
	{
		this.repository = repository;
	}

	public List<Product> getProducts()
	{
		return repository.findAll();
	}

	@KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
	public void processProductEvents(ProductEvent productEvent)
	{
		Product product = productEvent.getProduct();
		if(productEvent.getEventType() == EventType.CreateProduct)
		{
			repository.save(product);
			log.info("New product successfully saved {}", product);
		}
		if(productEvent.getEventType() == EventType.UpdateProduct)
		{
			Product existingProduct = repository.findById(product.getId()).get();
			existingProduct.setName(product.getName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setDescription(product.getDescription());
			repository.save(existingProduct);

			log.info("Existing product {} got successfully updated to {}", existingProduct, product);
		}
	}
}

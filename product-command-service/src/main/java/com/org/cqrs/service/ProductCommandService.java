package com.org.cqrs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.org.cqrs.constant.EventType;
import com.org.cqrs.dto.ProductEvent;
import com.org.cqrs.entity.Product;
import com.org.cqrs.repository.ProductRepository;

@Service
public class ProductCommandService
{
	private ProductRepository repository;
	private KafkaTemplate<String, Object> kafkaTemplate;
	private final String productEventTopic;

	public ProductCommandService(ProductRepository repository, KafkaTemplate<String, Object> kafkaTemplate,
			@Value("${product.event.topic-name}") String productEventTopic)
	{
		this.repository = repository;
		this.kafkaTemplate = kafkaTemplate;
		this.productEventTopic = productEventTopic;
	}

	public Product createProduct(ProductEvent productEvent)
	{
		Product productDO = repository.save(productEvent.getProduct());
		ProductEvent event = new ProductEvent(EventType.CreateProduct, productDO);

		kafkaTemplate.send(productEventTopic, event);

		return productDO;
	}

	public Product updateProduct(long id, ProductEvent productEvent)
	{
		Product newProduct = productEvent.getProduct();

		Product existingProduct = repository.findById(id).get();
		existingProduct.setName(newProduct.getName());
		existingProduct.setPrice(newProduct.getPrice());
		existingProduct.setDescription(newProduct.getDescription());

		Product productDO = repository.save(existingProduct);
		ProductEvent event = new ProductEvent(EventType.UpdateProduct, productDO);
		kafkaTemplate.send("product-event-topic", event);

		return productDO;
	}

}

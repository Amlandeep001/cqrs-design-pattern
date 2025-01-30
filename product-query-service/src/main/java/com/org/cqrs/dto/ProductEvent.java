package com.org.cqrs.dto;

import com.org.cqrs.constant.EventType;
import com.org.cqrs.entity.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent
{
	EventType eventType;
	Product product;
}

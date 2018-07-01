package com.tiaa.assignment.service;

import java.util.concurrent.ConcurrentHashMap;

import com.tiaa.assignment.model.Product;

public class EquipmentCacheService implements CacheService<Product, Long> {
	
	private ConcurrentHashMap<Product, Long> products = new ConcurrentHashMap<>();

	@Override
	public Long put(Product key, Long value) {
		return products.put(key, value);
	}

	@Override
	public Long get(Product key) {
		// TODO Auto-generated method stub
		return null;
	}

}

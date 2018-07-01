package com.tiaa.assignment.service;

public interface CacheService<K, V> {

	V put(K key, V value);

	V get(K key);
}

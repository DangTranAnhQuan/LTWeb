package com.example.demo.services;

import com.example.demo.entity.Product;
import java.util.Optional;
import java.util.List;

public interface ProductService {
	List<Product> findAllOrderByPriceAsc();

	List<Product> findByCategoryId(Integer categoryId);

	List<Product> findAll();

	Product save(Product product);

	void deleteById(Integer id);

	Optional<Product> findById(Integer id);
}
package com.example.demo.services.impl;

import com.example.demo.Repository.ProductRepository;

import com.example.demo.entity.Product;
import com.example.demo.services.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAllOrderByPriceAsc() {
		return productRepository.findAllByOrderByPriceAsc();
	}

	@Override
	public List<Product> findByCategoryId(Integer categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}
}
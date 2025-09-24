package com.example.demo.Repository;

import com.example.demo.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findAllByOrderByPriceAsc();

	List<Product> findByCategoryId(Integer categoryId);

	Page<Product> findByTitleContaining(String title, Pageable pageable);
}
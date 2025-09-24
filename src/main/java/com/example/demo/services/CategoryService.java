package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Category;

public interface CategoryService {

	Page<Category> findByCategoryNameContaining(String name, Pageable pageable);

	List<Category> findByCategoryNameContaining(String name);

	void deleteById(Integer id);

	Optional<Category> findById(Integer id);

	List<Category> findAll();

	Page<Category> findAll(Pageable pageable);

	<S extends Category> S save(S entity);

	long count();
	
	void delete(Category entity);
    <S extends Category> Optional<S> findOne(Example<S> example);
    List<Category> findAllById(Iterable<Integer> ids);
    List<Category> findAll(Sort sort);
    Optional<Category> findByCategoryName(String name);
}

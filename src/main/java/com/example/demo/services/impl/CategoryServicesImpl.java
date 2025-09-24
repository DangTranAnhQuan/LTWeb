//package com.example.demo.services.impl;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
//import com.example.demo.Repository.CategoryRepository;
//import com.example.demo.entity.Category;
//import com.example.demo.services.CategoryService;
//
//@Service
//@Transactional
//public class CategoryServicesImpl implements CategoryService {
//	@Autowired
//	CategoryRepository categoryRepository;
//
//	public CategoryServicesImpl(CategoryRepository categoryRepository) {
//		this.categoryRepository = categoryRepository;
//	}
//
//	@Override
//	public <S extends Category> S save(S entity) {
//		// Thêm mới
//		if (entity.getId() == null) {
//			return categoryRepository.save(entity);
//		}
//
//		// Cập nhật
//		Category old = categoryRepository.findById(entity.getId())
//				.orElseThrow(() -> new IllegalArgumentException("Category not found: " + entity.getId()));
//
//		// Nếu tên trống thì giữ tên cũ
//		if (!org.springframework.util.StringUtils.hasText(entity.getCategoryName())) {
//			entity.setCategoryName(old.getCategoryName());
//		}
//
//		return categoryRepository.save(entity);
//	}
//
//	@Override
//	public Page<Category> findAll(Pageable pageable) {
//		return categoryRepository.findAll(pageable);
//	}
//
//	@Override
//	public long count() {
//		return categoryRepository.count();
//	}
//
//	@Override
//	public List<Category> findAll() {
//		return categoryRepository.findAll();
//	}
//
//	@Override
//	public Optional<Category> findById(Integer id) {
//		return categoryRepository.findById(id);
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		categoryRepository.deleteById(id);
//	}
//
//	@Override
//	public List<Category> findByCategoryNameContaining(String name) {
//		return categoryRepository.findByCategoryNameContaining(name);
//	}
//
//	@Override
//	public Page<Category> findByCategoryNameContaining(String name, Pageable pageable) {
//		return categoryRepository.findByCategoryNameContaining(name, pageable);
//	}
//	
//	
//}

package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.demo.Repository.CategoryRepository;
import com.example.demo.entity.Category;
import com.example.demo.services.CategoryService;

@Service
@Transactional
public class CategoryServicesImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServicesImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public <S extends Category> S save(S entity) {
        if (entity.getId() == null) {
            return categoryRepository.save(entity);
        }
        Category old = categoryRepository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + entity.getId()));

        if (!StringUtils.hasText(entity.getCategoryName())) {
            entity.setCategoryName(old.getCategoryName());
        }
        if (!StringUtils.hasText(entity.getIcon())) {
            entity.setIcon(old.getIcon());
        }
        if (!StringUtils.hasText(entity.getImages())) {
            entity.setImages(old.getImages());
        }
        if (entity.getUserId() == null) {
            entity.setUserId(old.getUserId());
        }
        return categoryRepository.save(entity);
    }

    @Override
    public Optional<Category> findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public void delete(Category entity) {
        categoryRepository.delete(entity);
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return categoryRepository.findOne(example);
    }

    @Override
    public List<Category> findAllById(Iterable<Integer> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findByCategoryNameContaining(String name) {
        return categoryRepository.findByCategoryNameContaining(name);
    }

    @Override
    public Page<Category> findByCategoryNameContaining(String name, Pageable pageable) {
        return categoryRepository.findByCategoryNameContaining(name, pageable);
    }
}

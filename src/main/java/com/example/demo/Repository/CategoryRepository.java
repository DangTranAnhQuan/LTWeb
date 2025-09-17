package com.example.demo.Repository;
//
//import java.util.List;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.example.demo.entity.Category;
//
//@Repository
//public interface CategoryRepository extends JpaRepository<Category, Integer >{
//	//Tìm Kiếm theo nội dung tên
//	 List<Category> findByCategoryNameContaining(String name);
//	 //Tìm kiếm và Phân trang
//	 Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
//}

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCategoryNameContaining(String name);
    Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
}

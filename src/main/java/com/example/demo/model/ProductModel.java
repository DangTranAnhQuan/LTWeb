package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductModel {
	private Integer id;

	@NotBlank(message = "Product title is required")
	private String title;

	@NotNull(message = "Quantity is required")
	@Min(value = 0, message = "Quantity must be non-negative")
	private Integer quantity;

	private String description;

	@NotNull(message = "Price is required")
	@Min(value = 0, message = "Price must be non-negative")
	private Double price;

	@NotNull(message = "Category is required")
	private Integer categoryId;

	private MultipartFile imageFile;
	private String image;

	private Integer userId;

	private Boolean isEdit = false;
}
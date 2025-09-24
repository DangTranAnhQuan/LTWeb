package com.example.demo.controller.graphql;

import com.example.demo.Repository.*;
import com.example.demo.entity.*;
import com.example.demo.services.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

record CreateProductInput(String title, int quantity, String description, double price, Integer userId,
		Integer categoryId) {
}

record UpdateProductInput(Integer id, String title, Integer quantity, String description, Double price) {
}

@Controller
public class ProductGraphQLController {

	private final ProductService productService;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	public ProductGraphQLController(ProductService productService, UserRepository userRepository,
			CategoryRepository categoryRepository) {
		this.productService = productService;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
	}

	@QueryMapping
	public List<Product> allProductsSortedByPrice() {
		return productService.findAllOrderByPriceAsc();
	}

	@QueryMapping
	public List<Product> productsByCategory(@Argument Integer categoryId) {
		return productService.findByCategoryId(categoryId);
	}

	@MutationMapping
	public Product createProduct(@Argument("product") CreateProductInput input) {
		User user = userRepository.findById(input.userId())
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		Category category = categoryRepository.findById(input.categoryId())
				.orElseThrow(() -> new IllegalArgumentException("Category not found"));

		Product product = new Product();
		product.setTitle(input.title());
		product.setQuantity(input.quantity());
		product.setDescription(input.description());
		product.setPrice(input.price());
		product.setUser(user);
		product.setCategory(category);

		return productService.save(product);
	}

	@MutationMapping
	public Boolean deleteProduct(@Argument Integer id) {
		productService.deleteById(id);
		return true;
	}

}
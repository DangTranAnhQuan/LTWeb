package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.Repository.ProductRepository;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

	private final ProductService productService;
	private final CategoryService categoryService;
	private final ProductRepository productRepository;

	public HomeController(ProductService productService, CategoryService categoryService,
			ProductRepository productRepository) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.productRepository = productRepository;
	}

	@GetMapping({ "", "/", "/home" })
	public String homePage(Model model) {

		List<Product> productList = productService.findAll();

		model.addAttribute("products", productList);

		return "web/home";
	}

	@GetMapping("/products")
	public String productsPage(Model model) {

		model.addAttribute("products", productService.findAll());
		model.addAttribute("categories", categoryService.findAll());

		return "web/products";
	}

	@GetMapping("/product/{id}")
	public String productDetail(@PathVariable("id") Integer id, Model model) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

		model.addAttribute("product", product);
		return "web/product-detail"; // Tạo file view này ở bước sau
	}
}
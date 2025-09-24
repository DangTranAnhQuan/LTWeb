package com.example.demo.controller.admin;

import com.example.demo.Repository.ProductRepository;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.model.ProductModel;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.services.IStorageService;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/products")
public class ProductControllerAddEdit {

	private final ProductService productService;
	private final CategoryService categoryService;
	private final UserService userService;
	private final ProductRepository productRepository;
	private final IStorageService storageService;

	public ProductControllerAddEdit(ProductService productService, CategoryService categoryService,
			UserService userService, ProductRepository productRepository, IStorageService storageService) { 
		this.productService = productService;
		this.categoryService = categoryService;
		this.userService = userService;
		this.productRepository = productRepository;
		this.storageService = storageService; 
	}

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryService.findAll();
	}

	@ModelAttribute("users")
	public List<User> getUsers() {
		return userService.findAll();
	}

	@GetMapping("")
	public String index() {
		return "redirect:/admin/products/searchpaginated?page=1&size=5";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		ProductModel productModel = new ProductModel();
		productModel.setIsEdit(false);
		model.addAttribute("product", productModel);
		return "admin/products/addOrEdit";
	}

	@GetMapping("edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") Integer id, RedirectAttributes ra) {
		Optional<Product> optProduct = productRepository.findById(id);
		if (optProduct.isEmpty()) {
			ra.addFlashAttribute("message", "Product is not existed!");
			return "redirect:/admin/products";
		}
		ProductModel productModel = new ProductModel();
		BeanUtils.copyProperties(optProduct.get(), productModel);

		productModel.setCategoryId(optProduct.get().getCategory().getId());
		productModel.setUserId(optProduct.get().getUser().getId());

		productModel.setIsEdit(true);
		model.addAttribute("product", productModel);
		return "admin/products/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(@Valid @ModelAttribute("product") ProductModel form, BindingResult result,
			RedirectAttributes ra) { 
		if (result.hasErrors()) {
			return "admin/products/addOrEdit";
		}

		Product entity = new Product();
		
		if (form.getImageFile() != null && !form.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			entity.setImage(storageService.getSorageFilename(form.getImageFile(), uuString));
			storageService.store(form.getImageFile(), entity.getImage());
		} else {
            if(form.getId() != null){
                Product oldProduct = productService.findById(form.getId()).orElse(null);
                if(oldProduct != null){
                    entity.setImage(oldProduct.getImage());
                }
            }
        }

		BeanUtils.copyProperties(form, entity);

		Category category = categoryService.findById(form.getCategoryId()).orElse(null);
		User user = userService.findById(form.getUserId()).orElse(null);
		entity.setCategory(category);
		entity.setUser(user);

		productService.save(entity);

		ra.addFlashAttribute("message", form.getIsEdit() ? "Product is edited!" : "Product is saved!");
		return "redirect:/admin/products";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
		productService.deleteById(id);
		ra.addFlashAttribute("message", "Product is deleted!");
		return "redirect:/admin/products";
	}

	@GetMapping("searchpaginated")
	public String searchPaginated(ModelMap model, @RequestParam(name = "title", required = false) String title,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("title"));

		Page<Product> resultPage = StringUtils.hasText(title) ? productRepository.findByTitleContaining(title, pageable)
				: productRepository.findAll(pageable);

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("title", title);
		model.addAttribute("productPage", resultPage);
		return "admin/products/searchpaginated";
	}
}
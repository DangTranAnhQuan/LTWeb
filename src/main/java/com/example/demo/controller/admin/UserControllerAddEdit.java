// File: src/main/java/com/example/demo/controller/admin/UserControllerAddEdit.java
package com.example.demo.controller.admin;

import com.example.demo.entity.User;
import com.example.demo.model.UserModel;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/users")
public class UserControllerAddEdit {

	private final UserService userService;

	private final com.example.demo.Repository.UserRepository userRepository;

	public UserControllerAddEdit(UserService userService, com.example.demo.Repository.UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@GetMapping("")
	public String index() {
		return "redirect:/admin/users/searchpaginated?page=1&size=5";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		UserModel userModel = new UserModel();
		userModel.setIsEdit(false);
		model.addAttribute("user", userModel);
		return "admin/users/addOrEdit";
	}

	@GetMapping("edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") Integer id, RedirectAttributes ra) {
		Optional<User> optUser = userService.findById(id);
		if (optUser.isEmpty()) {
			ra.addFlashAttribute("message", "User is not existed!");
			return "redirect:/admin/users/searchpaginated?page=1&size=5";
		}
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(optUser.get(), userModel);
		userModel.setPassword(""); 
		userModel.setIsEdit(true);
		model.addAttribute("user", userModel);
		return "admin/users/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(@Valid @ModelAttribute("user") UserModel form, BindingResult result,
			RedirectAttributes ra) {
		if (result.hasErrors()) {
			return "admin/users/addOrEdit";
		}

		User entity = new User();

		if (form.getId() != null) {
			User oldUser = userService.findById(form.getId()).orElse(new User());
			entity.setPassword(oldUser.getPassword()); 
		}

		BeanUtils.copyProperties(form, entity);

		if (StringUtils.hasText(form.getPassword())) {
			entity.setPassword(form.getPassword());
		}

		userService.save(entity);

		ra.addFlashAttribute("message", Boolean.TRUE.equals(form.getIsEdit()) ? "User is edited!" : "User is saved!");
		return "redirect:/admin/users/searchpaginated?page=1&size=5";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
		userService.deleteById(id);
		ra.addFlashAttribute("message", "User is deleted!");
		return "redirect:/admin/users/searchpaginated?page=1&size=5";
	}

	@GetMapping("searchpaginated")
	public String searchPaginated(ModelMap model, @RequestParam(name = "username", required = false) String username,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("username"));

		Page<User> resultPage = StringUtils.hasText(username)
				? userRepository.findByUsernameContaining(username, pageable)
				: userRepository.findAll(pageable);

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("username", username);
		model.addAttribute("userPage", resultPage);

		return "admin/users/searchpaginated";
	}
}
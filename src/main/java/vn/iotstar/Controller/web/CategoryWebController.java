package vn.iotstar.Controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class CategoryWebController {

	@GetMapping
	public String showCategoryPage() {
		return "admin/categories";
	}
}
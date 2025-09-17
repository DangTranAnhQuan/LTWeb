package com.example.demo.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Category;
import com.example.demo.model.CategoryModel;
import com.example.demo.services.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryControllerAddEdit {

    private final CategoryService categoryService;
    public CategoryControllerAddEdit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String index() {
        return "redirect:/admin/categories/searchpaginated?page=1&size=5";
    }

    @GetMapping("add")
    public String add(ModelMap model) {
        CategoryModel m = new CategoryModel();
        m.setIsEdit(false);
        model.addAttribute("category", m);
        return "admin/categories/addOrEdit";
    }

    @GetMapping("edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") Integer id, RedirectAttributes ra) {
        Optional<Category> opt = categoryService.findById(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("message", "Category is not existed!");
            return "redirect:/admin/categories/searchpaginated?page=1&size=5";
        }
        CategoryModel m = new CategoryModel();
        BeanUtils.copyProperties(opt.get(), m); // copy id, categoryName
        m.setIsEdit(true);
        model.addAttribute("category", m);
        return "admin/categories/addOrEdit";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@Valid @ModelAttribute("category") CategoryModel form,
                               BindingResult result,
                               RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "admin/categories/addOrEdit";
        }
        Category entity = new Category();
        BeanUtils.copyProperties(form, entity); // id + categoryName

        categoryService.save(entity);

        ra.addFlashAttribute("message",
                Boolean.TRUE.equals(form.getIsEdit()) ? "Category is edited!" : "Category is saved!");
        return "redirect:/admin/categories/searchpaginated?page=1&size=5";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        categoryService.deleteById(id);
        ra.addFlashAttribute("message", "Category is deleted!");
        return "redirect:/admin/categories/searchpaginated?page=1&size=5";
    }

    @GetMapping("searchpaginated")
    public String searchPaginated(ModelMap model,
                                  @RequestParam(name = "name", required = false) String name,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("categoryName"));
        Page<Category> resultPage = StringUtils.hasText(name)
                ? categoryService.findByCategoryNameContaining(name, pageable)
                : categoryService.findAll(pageable);

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end   = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) start = end - 5;
                else if (start == 1)   end   = start + 5;
            }
            List<Integer> pageNumbers =
                    IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("name", name);
        model.addAttribute("categoryPage", resultPage);
        return "admin/categories/searchpaginated";
    }
}

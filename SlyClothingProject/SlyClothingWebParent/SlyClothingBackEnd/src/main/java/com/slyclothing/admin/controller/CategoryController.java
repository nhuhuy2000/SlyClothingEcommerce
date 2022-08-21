package com.slyclothing.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import com.slyclothing.admin.service.CategoryService;
import com.slyclothing.common.entity.Category;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories/list")
	public String getListCategory(Model model) {
		List<Category> categories = categoryService.listCategory();
		model.addAttribute("categories", categories);
		return "page_category";
	}

	@GetMapping("/categories/register")
	public String saveCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "page_category_register";
	}

	@PostMapping("/categories/save")
	public String saveCategory(Category category, Model model, RedirectAttributes redirectAttributes) {
		categoryService.saveCategory(category);
		redirectAttributes.addAttribute("message", "Save Category Successfully");
		return "redirect:/categories/list";
	}
}

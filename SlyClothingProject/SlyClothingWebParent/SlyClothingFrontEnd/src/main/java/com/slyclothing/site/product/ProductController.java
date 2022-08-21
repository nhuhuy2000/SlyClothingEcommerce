package com.slyclothing.site.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.slyclothing.common.entity.Category;
import com.slyclothing.common.entity.Product;
import com.slyclothing.site.category.CategoryNotFoundException;
import com.slyclothing.site.category.CategoryService;

@Controller
public class ProductController {
	@Autowired private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/categories/{category_name}")
	public String viewCategoryFirstPage(@PathVariable("category_name") String name, Model model) {
		return viewCategoryByPage(name, 1, model);
	}
	@GetMapping("/categories/{category_name}/page/{pageNum}")
	public String viewCategoryByPage(@PathVariable("category_name") String name, @PathVariable("pageNum") int pageNum, Model model) {
	try {
		
		Category category =	categoryService.getCategory(name);
		
		
		Page<Product> pageProducts = productService.listByCategory(pageNum, category.getId());
		List<Product> listProducts = pageProducts.getContent();
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
		long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
		if(endCount > pageProducts.getTotalElements()) {
			endCount = pageProducts.getTotalElements();
		}
		
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", pageProducts.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", pageProducts.getTotalElements());
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("category", category);
		model.addAttribute("pageTitle", category.getName());
		return "";
	}catch (CategoryNotFoundException e) {
		// TODO: handle exception
		return "error/404";
	}
	}
	@GetMapping("/products/{product_name}")
	public String viewProductDetail(@PathVariable("product_name") String name, Model model) throws CategoryNotFoundException {
		try {
			
			Product product = productService.getProduct(name);
			Category category = categoryService.getCategory(product.getCategory().getName());
			
			model.addAttribute("category", category);
			model.addAttribute("product", product);
			model.addAttribute("pageTitle", product.getShortName());
			return "single-product";
		} catch (ProductNotFoundException e) {
			// TODO: handle exception
			return "error/404";
		}
	}
	@GetMapping("/search/page/{pageNum}")
	public String searchByPage(@Param("keyword") String keyword,
			@PathVariable("pageNum") int pageNum,Model model) {
		Page<Product> pageProducts = productService.search(keyword, pageNum);
		List<Product> listResult = pageProducts.getContent();
		long startCount = (pageNum - 1) * ProductService.SEARCH_RESULTS_PER_PAGE + 1;
		long endCount = startCount + ProductService.SEARCH_RESULTS_PER_PAGE - 1;
		if(endCount > pageProducts.getTotalElements()) {
			endCount = pageProducts.getTotalElements();
		}
		
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", pageProducts.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", pageProducts.getTotalElements());
		model.addAttribute("pageTitle", keyword + " - Search Result");
		model.addAttribute("keyword", keyword);
		model.addAttribute("listResult", listResult);
		
		return "";
	}
	@GetMapping("/search")
	public String searchFirstPage(@Param("keyword") String keyword, Model model){
		return searchByPage(keyword, 1, model);
	}
}

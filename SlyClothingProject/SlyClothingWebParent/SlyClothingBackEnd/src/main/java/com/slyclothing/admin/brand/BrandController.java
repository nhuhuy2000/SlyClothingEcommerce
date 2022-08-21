package com.slyclothing.admin.brand;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.slyclothing.admin.service.CategoryService;
import com.slyclothing.admin.utility.FileUploadUtil;
import com.slyclothing.common.entity.Brand;
import com.slyclothing.common.entity.Category;

@Controller
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/brands")
	public String listBrands(Model model) {
		List<Brand> listBrands = brandService.listAll();
		model.addAttribute("listBrands", listBrands);
		return "brand/page_brand";
	}
	@GetMapping("/brands/register")
	public String registerBrand(Model model) {
		Brand brand = new Brand();
		List<Category> list = categoryService.listCategory();
		model.addAttribute("brand", brand);
		model.addAttribute("listCategory", list);
		return "brand/page_brand_register";
	}
	@PostMapping("/brands/save")
	public String saveBrand(Brand brand, @RequestParam("fileImage") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) throws IOException {
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			brand.setLogo(fileName);
			
			Brand savedBrand = brandService.save(brand);
			String uploadDirString = "../brand-logos/" + savedBrand.getId();
			
			FileUploadUtil.cleanDir(uploadDirString);
			FileUploadUtil.saveFile(uploadDirString, fileName, multipartFile);
		}else {
			brandService.save(brand);
		}
		redirectAttributes.addFlashAttribute("message", "The brand has been saved successfully");
		
		return "redirect:/brands";
		
	}
	@GetMapping("/brands/edit/{id}")
	public String editBrand(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Brand brand = brandService.get(id);
			List<Category> listCategories = categoryService.listCategory();
			
			model.addAttribute("brand", brand);
			model.addAttribute("listCategories", listCategories);
			model.addAttribute("pageTitle", "Edit Brand (ID: " + id + ")");
			
			return "brand/page_brand_register";
		}catch(BrandNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			return "redirect:/brands";
		}
	}
	@GetMapping("/brands/delete/{id}")
	public String deleteBrand(@PathVariable(name ="id") Integer id
			,Model model, RedirectAttributes redirectAttributes) {
		try {
			brandService.delete(id);
			String brandDir = "../brand-logos/" + id;
			FileUploadUtil.cleanDir(brandDir);
			redirectAttributes.addFlashAttribute("message", "The brand ID " + id + "has been deleted successfully");
			
		}catch (BrandNotFoundException e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("message", redirectAttributes);
		}
		return "redirect:/brands";
	}
	
}

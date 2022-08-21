package com.slyclothing.admin.product;





import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.slyclothing.admin.brand.BrandService;
import com.slyclothing.admin.security.AdminUserDetails;
import com.slyclothing.admin.service.CategoryService;
import com.slyclothing.admin.utility.FileUploadUtil;
import com.slyclothing.common.entity.Brand;
import com.slyclothing.common.entity.Category;
import com.slyclothing.common.entity.Product;
import com.slyclothing.common.entity.ProductImage;

@Controller
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/products")
	public String listAll(Model model) {
		List<Product> listProducts = productService.listAll();
		model.addAttribute("listProducts", listProducts);
		return "products/products";
	}
//	@GetMapping("/products")
//	public String listFirstPage(Model model) {
//		return listByPage(1, "name", "asc", null, model, 0);
//	}
	@GetMapping("/products/new")
	public String newProduct(Model model) {
		List<Brand> listBrands = brandService.listAll();
		
		Product product = new Product();
		product.setEnabled(true);
		product.setInStock(true);
		model.addAttribute("product", product);
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("pageTitle", "Create New Product");
		model.addAttribute("numberOfExistingExtraImages", 0);
		return "products/product_form";
	}
//	@PostMapping("/products/save")
//	public String saveProduct(Product product, RedirectAttributes redirectAttributes,
//			@RequestParam(value ="fileImage", required = false) MultipartFile mainImageMultipart,
//			@RequestParam(value ="extraImage") MultipartFile[] extraMultipartFiles
//			,@RequestParam(name = "detailIDs", required = false) String[] detailIDs
//			,@RequestParam(name = "detailNames", required = false) String[] detailNames,
//			@RequestParam(name = "detailValues", required = false) String[] detailValues
//			,@RequestParam(name = "imageIDs", required = false) String[] imageIDs,
//			@RequestParam(name = "imageNames", required = false) String[] imageNames
//			,@AuthenticationPrincipal AdminUserDetails loggedDetails
//			) throws IOException {
//			if(loggedDetails.hasRole("Saleperson")) {
//				productService.saveProductPrice(product);
//				redirectAttributes.addFlashAttribute("message", "The product has been saved successfully");
//				return "redirect:/products";
//			}
//			ProductSaveHelper.setMainImageName(mainImageMultipart, product);
//			ProductSaveHelper.setExistingExtraImageNames(imageIDs, imageNames, product);
//			ProductSaveHelper.setNewExtraImageNames(extraMultipartFiles, product);
//			ProductSaveHelper.setProductDetails(detailIDs,detailNames, detailValues, product);
//			Product savedProduct = productService.save(product);
//			ProductSaveHelper.savedUploadedImage(mainImageMultipart, extraMultipartFiles, savedProduct);
//			
//			ProductSaveHelper.deleteExtraImageWeredRemovedOnForm(product);
//		
//		redirectAttributes.addFlashAttribute("message", "The product has been saved success!");
//		
//		return "redirect:/products";
//	}
	
	////////////////////////////REplace------
	@PostMapping("/products/save")
	public String saveProduct(Product product, RedirectAttributes redirectAttributes,
			@RequestParam(value ="fileImage") MultipartFile mainImageMultipart,
			@RequestParam(value = "extraImage") MultipartFile[]  extraImageMultipartFiles) throws IOException {
				
			setMainImageName(mainImageMultipart, product);
			setExtraImageNames(extraImageMultipartFiles, product);
				Product savedProduct = productService.save(product);
				saveUploadedImages(mainImageMultipart, extraImageMultipartFiles, savedProduct);
				
			
		
		redirectAttributes.addFlashAttribute("message", "The product has been saved success!");
		
		return "redirect:/products";
	}
	// 
	private void saveUploadedImages(MultipartFile mainImageMultipart, MultipartFile[] extraMultipartFiles,
			Product savedProduct) throws IOException {
		// TODO Auto-generated method stub
		if(!mainImageMultipart.isEmpty()) {
			String fileName = StringUtils.cleanPath(mainImageMultipart.getOriginalFilename());
			
			String uploadDirString = "../product-images/" + savedProduct.getId();
			
			FileUploadUtil.cleanDir(uploadDirString);
			FileUploadUtil.saveFile(uploadDirString, fileName,  mainImageMultipart);
		}
		if(extraMultipartFiles.length > 0) {
			String uploadDirString = "../product-images/" + savedProduct.getId() + "/extras";
			for(MultipartFile multipartFile : extraMultipartFiles) {
				if(multipartFile.isEmpty()) {
					continue;
				}
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				FileUploadUtil.saveFile(uploadDirString, fileName,  multipartFile);
			}
		}
	}
	////////////////
	private void setExtraImageNames(MultipartFile[] extraMultipartFiles, Product product) {
		if(extraMultipartFiles.length > 0) {
			for(MultipartFile multipartFile : extraMultipartFiles) {
				if(!multipartFile.isEmpty()) {
					String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
					if(!product.containsImageName(fileName)) {						
						product.addExtraImage(fileName);
					}
				}
			}
		}
	}
	//
	private void setMainImageName(MultipartFile mainImageMultipart, Product product) {
		if(!mainImageMultipart.isEmpty()) {
			String fileName = StringUtils.cleanPath(mainImageMultipart.getOriginalFilename());
			product.setMainImage(fileName);
		}
	}
	
//	@PostMapping("/products/save")
//	public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
//		productService.save(product);
//		redirectAttributes.addFlashAttribute("message", "Lưu sản phẩm thành công");
//		
//		return "redirect:/products";
//	}
	
	
	@GetMapping("/products/{id}/enabled/{status}")
	public String updateProductEnabledStatus(@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		productService.updateProductEnabledStatus(id, enabled);
		String statuString = enabled ? "enabled" : "disabled";
		String message = "The Product ID" + id + "has been" + statuString;
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/products";
	}
	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {
		try {
			productService.delete(id);
			String productExtraImageDir = "../product-images/" + id + "/extras";
			String productImageDir = "../product-images/" + id;
			FileUploadUtil.cleanDir(productExtraImageDir);
			FileUploadUtil.cleanDir(productImageDir);
			redirectAttributes.addFlashAttribute("message", "The product Id "+ id + "has been deleted successfully");
		}catch (ProductNotFoundException e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/products";
	}
	@GetMapping("/products/edit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			
			Product product = productService.get(id);
			List<Brand> listBrands = brandService.listAll();
			Integer numberOfExistingExtraImages = product.getImages().size();
			model.addAttribute("product", product);
			model.addAttribute("pageTitle", "Edit Product (ID: " + id +")");
			model.addAttribute("listBrands", listBrands);
			model.addAttribute("numberOfExistingExtraImages", numberOfExistingExtraImages);
			return "products/product_form";
		}catch (ProductNotFoundException e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "products/product_form";
	}
	@GetMapping("/products/detail/{id}")
	public String viewProductDetails(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			
			Product product = productService.get(id);
			
			model.addAttribute("product", product);
			
			
			return "products/product_detail_modal";
		}catch (ProductNotFoundException e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "products/product_form";
	}
	@GetMapping("/products/page/{pageNum}")
	public String listByPage(
		@PathVariable(name = "pageNum")int pageNum,
		@Param("sortField") String sortField, @Param("sortDir") String sortDir,
		@Param("keyword") String keyword,
		@Param("categoryId") Integer categoryId,
		Model model) {
		Page<Product> page = productService.listByPage(pageNum, sortField, sortDir, keyword, categoryId);
		List<Product> listProducts = page.getContent();
		
		List<Category> listCategories = categoryService.listCategory();
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
		long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
		if(endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		if(categoryId != null) {
			model.addAttribute("categoryId", categoryId);
		}
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("listCategories", listCategories);
		return "products/products";
	}
}

package com.slyclothing.site.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.slyclothing.common.entity.Product;

@Service
public class ProductService {
	public static final int PRODUCTS_PER_PAGE = 10;
	public static final int SEARCH_RESULTS_PER_PAGE = 10;
	@Autowired
	private ProductRepository productRepository;
	
	public Page<Product> listByCategory(int pageNum, Integer categoryId){
		
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		
		return productRepository.listByCategory(categoryId, pageable);
	}
	public Product getProduct(String name) throws ProductNotFoundException {
		Product product = productRepository.findByName(name);
		if(product == null ) {
			throw new ProductNotFoundException("Could not find any product with name: " + product.getName());
		}
		return product;
	}
	public Page<Product> search(String keyword, int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULTS_PER_PAGE);
		return productRepository.search(keyword, pageable);
	}
}

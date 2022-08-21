package com.slyclothing.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slyclothing.admin.repository.CategoryRepository;
import com.slyclothing.common.entity.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public List<Category> listCategory() {
		return categoryRepository.findAll();
	}
}

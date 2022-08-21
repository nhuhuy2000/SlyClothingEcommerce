package com.slyclothing.site.category;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slyclothing.common.entity.Category;

import net.bytebuddy.asm.Advice.Return;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> listCategories(){
		
		List<Category> listEnabledCategories = categoryRepository.findAllEnabled();
		
		return listEnabledCategories;
	}
	
	public Category getCategory(String name) throws CategoryNotFoundException {
		Category category = categoryRepository.findByNameEnabled(name);
		if(category == null) {
			throw new CategoryNotFoundException("Could not find any category with name:" + category.getName() );
		}
		
		return category;
	}
}

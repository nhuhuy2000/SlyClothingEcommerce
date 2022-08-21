package com.slyclothing.site.category;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.slyclothing.common.entity.Category;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
//	@Test
//	public void testListEnabledCategories() {
//		List<Category> categories = categoryRepository.findAllEnabled();
//		categories.forEach(category -> {
//			System.out.println(category.getName() + " (" + category.isEnabled() + ")");
//		});
//	}
}

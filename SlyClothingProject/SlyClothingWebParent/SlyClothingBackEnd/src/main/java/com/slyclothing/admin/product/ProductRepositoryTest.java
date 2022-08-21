package com.slyclothing.admin.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.slyclothing.common.entity.Brand;
import com.slyclothing.common.entity.Category;
import com.slyclothing.common.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	@Disabled
	public void testCreateProduct() {
		Brand brand = entityManager.find(Brand.class, 1);
		Category category = entityManager.find(Category.class, 1);
		Product product = new Product(2, "Quan Ao Nam 2", "Ao nay dep!", "Loremfdskfd", "fdfdsfdsfd", true, new Date(), new Date(), false, 0, 0, category, brand);
		
		Product savedProduct = repository.save(product);
		
		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getId()).isGreaterThanOrEqualTo(0);
		
	}
	@Test
	@Disabled
	public void testListAllProducts() {
		Iterable<Product> interableProductsIterable = repository.findAll();
		
		interableProductsIterable.forEach(System.out::println);
	}
	@Test
	@Disabled
	public void testGetProduct() {
		Integer id = 1;
		Product product = repository.findById(id).get();
		System.out.println(product);
		assertNotNull(product);
	}
	@Test
	@Disabled
	public void testUpdateProduct() {
		Product product = repository.findById(1).get();
		product.setName("Ao Nam 2");
		product.setAlias("Ao nay khong dep!");
		Product savedProduct = repository.save(product);
		assertNotNull(savedProduct);
	}
	@Test
	@Disabled
	public void testDeleteProduct() {
		repository.delete(null);
	}
	@Test
	@Disabled
	public void testSaveProductWithImages() {
		Integer productId = 1;
		Product product = repository.findById(productId).get();
		product.setMainImage("main image.jpg");
		product.addExtraImage("extra_image_1.png");
		product.addExtraImage("extra_image_2.png");
		product.addExtraImage("extra_image_3.png");
		
		Product savedProduct = repository.save(product);
		assertThat(savedProduct.getImages().size()).isEqualTo(3);
	}
	@Test
	public void testSaveProductWithDetails() {
		Integer productId = 1;
		Product product = repository.findById(productId).get();
		product.addDetail("Test detail 1", "1");
		product.addDetail("Test detail 2", "2");
		product.addDetail("Test detail 3", "3");
		
		Product savedProduct = repository.save(product);
		assertThat(savedProduct.getDetails()).isNotEmpty();
	}
}

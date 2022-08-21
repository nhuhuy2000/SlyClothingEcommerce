package com.slyclothing.site.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slyclothing.common.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p WHERE p.enabled = true" + "AND (p.category.id = ?1)"
	+ " ORDER BY p.name ASC")
	public Page<Product> listByCategory(Integer categoryId, Pageable pageable);
	
	public Product findByName(String name);
	
	@Query(value = "SELECT * FROM products WHERE enabled = true AND " + "MATCH(name, short_description, full_description) AGAINT (?1)", nativeQuery = true)
	public Page<Product> search(String keyword, Pageable pageable);
}

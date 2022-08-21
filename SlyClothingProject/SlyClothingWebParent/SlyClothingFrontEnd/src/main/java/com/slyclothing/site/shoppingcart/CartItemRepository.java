//package com.slyclothing.site.shoppingcart;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//
//import com.slyclothing.common.entity.CartItem;
//import com.slyclothing.common.entity.Customer;
//import com.slyclothing.common.entity.Product;
//
//public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
//	public List<CartItem> findByCustomer(Customer customer);
//	
//	public CartItem findByCustomerAndProduct(Customer customer, Product product);
//	
//	@Modifying
//	@Query("UPDATE CartItem c SET c.quantity = ?1  AND c.product.id = ?2")
//	public void updateQuantity(Integer quantity, Integer productId);
//	
//	@Modifying
//	@Query("DELETE FROM CartItem c WHERE c.product.id = ?1")
//	public void deleteByProduct(Integer productId);
//}

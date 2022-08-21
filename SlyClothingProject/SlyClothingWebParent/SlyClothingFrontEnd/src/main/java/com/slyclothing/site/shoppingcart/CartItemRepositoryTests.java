//package com.slyclothing.site.shoppingcart;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//import com.slyclothing.common.entity.CartItem;
//import com.slyclothing.common.entity.Customer;
//import com.slyclothing.common.entity.Product;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//public class CartItemRepositoryTests {
//	@Autowired
//	private CartItemRepository cartItemRepository;
//	@Autowired
//	private TestEntityManager entityManager;
//	@Test
//
//	public void testSaveItem() {
//		Integer customerId = 1;
//		Integer productId = 1;
//		
//		Customer customer = entityManager.find(Customer.class, customerId);
//		Product product = entityManager.find(Product.class, productId);
//		
//		CartItem newItem = new CartItem();
//		
//		newItem.setCustomer(customer);
//		newItem.setProduct(product);
//		newItem.setQuantity(2);
//		CartItem savedCartItem = cartItemRepository.save(newItem);
//		assertThat(savedCartItem.getId()).isEqualTo(5);
//	}
//	@Test
//	@Disabled
//	public void testFindByCustomer() {
//		Integer customerIdInteger = 1;
//		List<CartItem> list = cartItemRepository.findByCustomer(new Customer(customerIdInteger)); 
//		assertThat(list.size()).isGreaterThan(0);
//	}
//	@Test
//	@Disabled
//	public void testFindByCustomerAndProduct() {
//		Integer customerId = 1;
//		Integer productId = 1;
//		
//		CartItem findByCustomerAndProduct = cartItemRepository.findByCustomerAndProduct(new Customer(1), new Product(productId));
//		
//		assertThat(findByCustomerAndProduct).isNotNull();
//	}
////	@Test
////	@Disabled
////	public void testUpdateQuantity() {
////		
////		cartItemRepository.updateQuantity(4, 1, 1);
////		
////		CartItem item = cartItemRepository.findByCustomerAndProduct(new Customer(1), new Product(1));
////		
////		assertThat(item.getQuantity()).isEqualTo(4);
////	}
////	@Test
////	@Disabled
////	public void testDeleteByCustomerAndProduct() {
////	 cartItemRepository.deleteByCustomerAndProduct(1, 1);
////	 CartItem findByCustomerAndProduct = cartItemRepository.findByCustomerAndProduct(new Customer(1), new Product(1));
////	 
////	 assertThat(findByCustomerAndProduct).isNull();
////	 
////	}
//}

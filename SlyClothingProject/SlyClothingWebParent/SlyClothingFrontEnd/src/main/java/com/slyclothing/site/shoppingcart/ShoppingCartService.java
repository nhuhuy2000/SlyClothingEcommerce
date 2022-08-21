//package com.slyclothing.site.shoppingcart;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.slyclothing.common.entity.CartItem;
//import com.slyclothing.common.entity.Customer;
//import com.slyclothing.common.entity.Product;
//
//@Service
//public class ShoppingCartService {
//	
//		@Autowired 
//		private CartItemRepository cartItemRepository;
//		
//		public Integer addProduct(Integer productId, Integer quantity, Customer customer) {
//			Integer updatedQuantity = quantity;
//			Product product = new Product(productId);
//			
//			CartItem cartItem = cartItemRepository.findByCustomerAndProduct(customer, product);
//			
//			if(cartItem != null) {
//				updatedQuantity = cartItem.getQuantity() + quantity;
//				cartItem.setQuantity(updatedQuantity);
//			}else {
//				cartItem = new CartItem();
//				cartItem.setCustomer(customer);
//				cartItem.setProduct(product);
//				
//			}
//			cartItem.setQuantity(updatedQuantity);
//			cartItemRepository.save(cartItem);
//			return updatedQuantity;
//		}
//}

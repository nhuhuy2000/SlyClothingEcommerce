//package com.slyclothing.site.shoppingcart;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ShoppingCartRestController {
//	@Autowired
//	private ShoppingCartService cartService;
//	
//	@PostMapping("/cart/add/{productId}/{quantity}")
//	public String addProductToCart(@PathVariable(name = "productId") Integer productId, @PathVariable("quantity") Integer quantity, HttpServletRequest request) {
//		Integer updateQuantity = cartService.addProduct(productId, quantity, null);
//		return "";
//	}
//}

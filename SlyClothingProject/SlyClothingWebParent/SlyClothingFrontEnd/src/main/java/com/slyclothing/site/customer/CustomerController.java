package com.slyclothing.site.customer;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.slyclothing.common.entity.Country;
import com.slyclothing.common.entity.Customer;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/regiter")
	public String showRegisterForm(Model model) {
		List<Country> listAllCountries = service.lisAllCountries();
		model.addAttribute("listCountries", listAllCountries);
		model.addAttribute("pageTitle", new Customer());
		return "register";
	}
}

package com.slyclothing.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("")
	public String viewHomePaeg() {
		return "index";
	}
	@GetMapping("/login")
	public String adminLogin() {
		return "admin_login";
	}
}

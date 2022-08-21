package com.slyclothing.admin.brand;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BrandNotFoundException extends Exception {
	public BrandNotFoundException(String message) {
		super(message);
	}
}

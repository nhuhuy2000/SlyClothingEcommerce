package com.slyclothing.admin.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminNotFoundException extends Exception {
	public AdminNotFoundException(String message) {
		super(message);
	}
}

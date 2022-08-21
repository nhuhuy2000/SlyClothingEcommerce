package com.slyclothing.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.slyclothing.admin.exception.AdminNotFoundException;
import com.slyclothing.admin.repository.AdminRepository;
import com.slyclothing.common.entity.Admin;

public class AdminUserDetailsService implements UserDetailsService{
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Admin admin = adminRepository.getAdminByEmail(email);
		if(admin != null) {
			return new AdminUserDetails(admin);
		}
		
		
		throw new UsernameNotFoundException("Could not find admin with email:"+ email);
	}
	
	
}

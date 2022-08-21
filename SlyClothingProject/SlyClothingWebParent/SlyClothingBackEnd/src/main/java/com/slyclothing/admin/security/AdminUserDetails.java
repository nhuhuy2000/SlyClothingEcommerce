package com.slyclothing.admin.security;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.slyclothing.common.entity.Admin;
import com.slyclothing.common.entity.Role;

public class AdminUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private Admin admin;
	
	public AdminUserDetails(Admin admin) {
		this.admin = admin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Role> roles = admin.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Role role : roles){
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return admin.getEmail();
	}
	public String getFullname() {
		return admin.getFirstName() + " " + admin.getLastName();
	}
	public void setFirstName(String firstName) {
		this.admin.setFirstName(firstName);
	}
	public void setLastName(String lastName) {
		this.admin.setLastName(lastName);
	}
	public String getName() {
		return admin.getFirstName();
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return admin.isEnabled();
	}
	public boolean hasRole(String roleName) {
		return admin.hasRole(roleName);
	}
}

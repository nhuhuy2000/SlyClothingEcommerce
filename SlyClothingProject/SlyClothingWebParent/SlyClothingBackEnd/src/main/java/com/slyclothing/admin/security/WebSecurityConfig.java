package com.slyclothing.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public AdminUserDetailsService adminDetailsService() {
		return new AdminUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(adminDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admins/**").hasAuthority("admin")
			.antMatchers("/admins/order").hasAnyAuthority("shipper", "saleperson")
			.antMatchers("/admins/login").hasAnyAuthority("shipper", "saleperson", "admin")
		.antMatchers("/products", "/products/", "/products/detail/**", "/products/page/**",
				"/products/edit/**", "/products/save", "/products/check_unique")
		.hasAnyAuthority("admin", "salesperson")
		
		.antMatchers("/products/new", "/products/delete/**").hasAuthority("admin")
		.anyRequest().authenticated()
		.and()
		.formLogin().usernameParameter("email").loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll()
		.and().rememberMe()
		.key("abcd_1234").tokenValiditySeconds(7 * 24 * 60 * 60);

	}
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception{
//		httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
//	}
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**", "/css/**");
	}
}

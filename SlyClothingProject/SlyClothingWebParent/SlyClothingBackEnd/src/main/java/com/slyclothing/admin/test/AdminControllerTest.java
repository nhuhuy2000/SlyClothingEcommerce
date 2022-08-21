package com.slyclothing.admin.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;



import com.slyclothing.admin.repository.AdminRepository;
import com.slyclothing.admin.service.AdminService;
import com.slyclothing.common.entity.Admin;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
	
	@Mock
	private AdminRepository adminRepository;
	@InjectMocks
	AdminService adminService;
	@Test
	public void testGetListAdmin() {
	
		List<Admin> list = new ArrayList<>();
		list.add(new Admin("nhuhuy2000@gmail.com", "nhuhuy16", "nhuhuy11", "nhuhuy12"));
		list.add(new Admin("nhuhuy20001@gmail.com", "nhuhuy161", "nhuhuy11", "nhuhuy14"));
		list.add(new Admin("nhuhuy20002@gmail.com", "nhuhuy162", "nhuhuy12", "nhuhuy13"));
		when(adminRepository.findAll()).thenReturn(list);
		List<Admin> listServiceAdmins = adminService.listAll();
		
		assertThat(listServiceAdmins.size()).isGreaterThanOrEqualTo(list.size());
	}
}

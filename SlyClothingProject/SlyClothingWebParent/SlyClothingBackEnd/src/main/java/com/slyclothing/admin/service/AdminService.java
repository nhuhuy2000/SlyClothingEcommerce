package com.slyclothing.admin.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.slyclothing.admin.exception.AdminNotFoundException;
import com.slyclothing.admin.repository.AdminRepository;
import com.slyclothing.admin.repository.RoleRepository;
import com.slyclothing.common.entity.Admin;
import com.slyclothing.common.entity.Role;

@Service
@Transactional
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static final int ADMINS_PER_PAGE = 4;
	
	public Admin saveAdmin(Admin admin){ 
		
		boolean isUpdatingUser = (admin.getId() != null);// Kiem tra co phai la update Admin hay khong
		if (isUpdatingUser) {// neu true
			Admin existingAdmin = adminRepository.findById(admin.getId()).get();// Gọi ra admin với id đó
			if (admin.getPassword().isEmpty()) {// Nếu người dùng không nhập gì
				admin.setPassword(existingAdmin.getPassword());// Thì ta giữ nguyên password đó
			} else {// Nếu có nhập thì ta mã hóa

				//
				// user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				encodePassword(admin);
			}
		} else {
			// user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			encodePassword(admin);
		}
		// encodePassword(user);//Má nó encode 2 lần cay vc!
		// user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return adminRepository.save(admin);
	}
	public List<Admin> listAll(){
		return (List<Admin>)adminRepository.findAll();
	}
	public List<Role> listRoles(){
		return (List<Role>) roleRepository.findAll();
	}
	public Admin getAdminByEmail(String email) {
		return adminRepository.getAdminByEmail(email);
	}
	private void encodePassword(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
	}
	public Admin updateAdmin(Admin adminInform) {
		Admin admin = adminRepository.findById(adminInform.getId()).get();

		if (adminInform.getPassword().isEmpty()) {
			admin.setPassword(admin.getPassword());
		
		}
		if (adminInform.getPhotos() != null) {
			admin.setPhotos(adminInform.getPhotos());
		}
		admin.setFirstName(adminInform.getFirstName());
		admin.setLastName(adminInform.getLastName());
		return adminRepository.save(admin);
	}
	public boolean isEmailUnique(Integer id, String email) {
		Admin adminByEmail = adminRepository.getAdminByEmail(email);// Lay ve user
		if (adminByEmail == null)// Neu user == null thi return true => tao moi
			return true;
		boolean isCreateNewAdmin = (id == null);// Kiem tra id co phai la new khong
		if (isCreateNewAdmin) {// Neu new
			if (adminByEmail != null)// Kiem tra cai email da ton tai chua?
				return false;
		} else {// Neu update
			if (adminByEmail.getId() != id)// Kiem tra id cua user muon update co khac voi id cua chinh no khong?
				return false;
		}
		return true;
	}
	public void deleteAdmin(Integer id) {
		adminRepository.deleteById(id);
	}

	public Admin getAdminByID(Integer id) throws AdminNotFoundException {
		try {

			return adminRepository.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new AdminNotFoundException("Cloud not find any admin with ID " + id);
		}
	}

	public void updateAdminEnabledStatus(Integer id, boolean enabled) {
		adminRepository.updateEnabledStatus(id, enabled);
	}

	public Page<Admin> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNum - 1, ADMINS_PER_PAGE, sort);
		if (keyword != null) {
			return adminRepository.fillAll(keyword, pageable);
		}
		return adminRepository.findAll(pageable);
	}
}

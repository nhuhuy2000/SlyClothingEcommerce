package com.slyclothing.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.slyclothing.admin.exception.AdminNotFoundException;
import com.slyclothing.admin.service.AdminService;
import com.slyclothing.admin.utility.FileUploadUtil;
import com.slyclothing.common.entity.Admin;
import com.slyclothing.common.entity.Role;



@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@GetMapping("/admins")
	public String listAll(Model model) {
		return "page_account";
	}
	@GetMapping("/admins/register")
	public String newAdmin(Model model) {
		Admin admin = new Admin();
		admin.setEnabled(true);
		List<Role> listRoles = adminService.listRoles();
		model.addAttribute("admin", admin);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("pageTitle", "Create New Admin");
		return "page_register";
	}
	@PostMapping("/admins/save")
	public String saveAdmin(Admin admin, RedirectAttributes redirect, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			admin.setPhotos(fileName);
			Admin saveAdmin = adminService.saveAdmin(admin);
			String uploadDir = "admin-photos/" + saveAdmin.getId(); // Tu folder user-photos> folder id
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} else {
			if(admin.getPhotos().isEmpty()) {
				admin.setPhotos(null);
			}
			adminService.saveAdmin(admin);
		}
		redirect.addFlashAttribute("message", "Save Admin Successfully!");
		//chưa thiết lập save và hiển thị tìm kiếm 
		return "redirect:/admins/list";
	}
	@GetMapping("/admins/edit/{id}")
	public String getAdminByID(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		Admin admin;
		try {
			admin = adminService.getAdminByID(id);
			List<Role> listRoles = adminService.listRoles();
			model.addAttribute("listRoles", listRoles);
			model.addAttribute("admin", admin);
			model.addAttribute("pageTitle", "Edit Admin (ID : " + id + ")");
			return "page_register";
		} catch (AdminNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/admins";
	}
	@GetMapping("/admins/delete/{id}")
	public String checkDeleteAdmin(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) throws AdminNotFoundException {
		adminService.deleteAdmin(id);
		redirectAttributes.addFlashAttribute("message", "The admin ID" + id + "has been deleted successfully");
		return "redirect:/admins/list";
	}
	@GetMapping("/admins/{id}/enabled/{status}")
	public String updateEnabledStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes) {
		adminService.updateAdminEnabledStatus(id, enabled);
		redirectAttributes.addFlashAttribute("message", "The admin ID:" + id + " has been updated enable " + enabled);
		return "redirect:/users";
	}
	@GetMapping("/admins/page/{pageNum}")
	public String listByPage(@PathVariable("pageNum") Integer pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {
		Page<Admin> page = adminService.listByPage(pageNum, sortField, sortDir, keyword);
		List<Admin> listUsers = page.getContent();
		// System.out.println("Page Num:" + pageNum);
		// System.out.println("Total element:" + page.getTotalElements());
		// System.out.println("Total page: " + page.getTotalPages());
		// System.out.println("content page: " + page.getContent());
		long startCount = (pageNum - 1) * AdminService.ADMINS_PER_PAGE + 1;
		long endCount = startCount + AdminService.ADMINS_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		List<Integer> arrPage = new ArrayList<Integer>();
		for (int i = 1; i <= page.getTotalPages(); i++) {
			arrPage.add(i);
		}
		//
		String sortFieldEmail = "email";
		String sortFieldLastName = "lastName";
		String sortFieldEnabled = "enabled";
		String sortFieldFirstName = "firstName";
		//
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", arrPage);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("lastPage", page.getTotalPages());
		model.addAttribute("sortFieldFirstName", sortFieldFirstName);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortFieldEmail", sortFieldEmail);
		model.addAttribute("sortFieldLastName", sortFieldLastName);
		model.addAttribute("sortFieldEnabled", sortFieldEnabled);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		return "page_account";

	}
	@GetMapping("/admins/list")
	public String listAdmin(Model model) {
		List<Admin> listUsers = adminService.listAll(); 
		
		return listByPage(1, model, "email", "asc", "");
	}
	
}

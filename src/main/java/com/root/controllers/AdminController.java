package com.root.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.root.exceptions.EmailUsernamePhoneAlreadyExists;
import com.root.models.Admin;
import com.root.services.AdminServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	AdminServiceImpl adminService;
	
	AdminController(AdminServiceImpl adminService){
		this.adminService = adminService;
	}
	
	@GetMapping("/admin")
	public String adminRegistration(Model model) {
		model.addAttribute("admin",new Admin());
		return "admin";
	}
	
	@GetMapping("/adminHome")
	public String adminHome(Model model,
			HttpSession session,
			RedirectAttributes redirectAttributes
			) {
		if(session.getAttribute("active admin")!=null && System.currentTimeMillis()-session.getLastAccessedTime()<30000)
		{
			Admin admin = (Admin) session.getAttribute("active admin");
			model.addAttribute("message","Welcome "+admin.getName());
			return "/adminHome";
		}
		else
			session.invalidate();
		redirectAttributes.addFlashAttribute("message","Please Login to Continue...");
		return "redirect:/adminLogin";
	}
	
	@GetMapping("/adminLogin")
	public String adminLogin(Model model) {
		model.addAttribute("admin",new Admin());
		return "adminLogin";
	}
	
	//Button mappings
	
	@PostMapping("/saveAdmin")
	public String saveAdmin(Admin admin, RedirectAttributes redirectAttributes) {
	    try {
	        adminService.newAdmin(admin);
	        redirectAttributes.addFlashAttribute("message", "New Admin Registered, Please Login to Continue...");
	    } catch (EmailUsernamePhoneAlreadyExists e) {
	        redirectAttributes.addFlashAttribute("message", e.getMessage());
	    }
	    return "redirect:/admin";
	}
	
	@PostMapping("/validateAdmin")
	public String validateAdmin(@RequestParam("adminId") long id,
			String password,
			RedirectAttributes redirectAttributes,
			HttpSession session) {
		Admin admin = adminService.validateAdmin(id,password);
		if(admin!=null) {
			session.setAttribute("active admin", admin);
			return "redirect:/adminHome";
		}
		else
			redirectAttributes.addFlashAttribute("message","Invalid Credentials");
		return "redirect:/adminLogin";
	}
}

package com.root.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.root.exceptions.EmailUsernamePhoneAlreadyExists;
import com.root.models.User;
import com.root.services.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	UserServiceImpl userService;
	
	UserController(UserServiceImpl userService){
		this.userService=userService;
	}
	
	@GetMapping("/user")
	public String userRegistration(Model model) {
		model.addAttribute("user",new User());
		return "user";
	}
	
	@GetMapping("/userLogin")
	public String userLogin(Model model) {
		model.addAttribute("user",new User());
		return "userLogin";
	}
	
	@GetMapping("/userHome")
	public String userHome(Model model,
			HttpSession session,
			RedirectAttributes redirectAttributes
			) {
		if(session.getAttribute("active user")!=null && System.currentTimeMillis()-session.getLastAccessedTime()<30000)
		{
			User user = (User) session.getAttribute("active user");
			model.addAttribute("message","Welcome "+user.getName());
			return "/userHome";
		}
		else
			session.invalidate();
		redirectAttributes.addFlashAttribute("message","Please Login to Continue...");
		return "redirect:/userLogin";
	}
	
	//Button Mappings
	
	@PostMapping("/saveUser")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
	    try {
	        userService.newUser(user);
	        redirectAttributes.addFlashAttribute("message", "New User Registered, Please Login to Continue...");
	    }catch (EmailUsernamePhoneAlreadyExists e) {
	        redirectAttributes.addFlashAttribute("message", e.getMessage());
	    }
	    return "redirect:/user";
	}

	
	@PostMapping("/validateUser")
	public String validateUser(String username,
			String password,
			RedirectAttributes redirectAttributes,
			HttpSession session) {
		User user = userService.validateUser(username,password);
		if(user!=null) {
			session.setAttribute("active user", user);
			return "redirect:/userHome";
		}
		else
			redirectAttributes.addFlashAttribute("message","Invalid Credentials");
		return "redirect:/userLogin";
	}

}

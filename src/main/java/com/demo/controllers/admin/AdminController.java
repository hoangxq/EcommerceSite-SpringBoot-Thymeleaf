package com.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.UserRegistrationDTO;
import com.demo.models.User;
import com.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String adminHome(Model model) {
		return "admin/home";
	}
	
	@GetMapping("/login")
	public String adminLogin() {
		return "admin/login";
	}
	
	@GetMapping("/register")
	public String adminRegisterGet(Model model) {
		model.addAttribute("user", new UserRegistrationDTO());
		return "admin/register";
	}
	
	@PostMapping("/register")
	public String adminRegisterPost(@ModelAttribute(name = "user") 
		UserRegistrationDTO registrationDTO) {
		
		userService.save(registrationDTO);
		return "redirect:/admin/register";
	}
	
	@GetMapping("/dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}
}


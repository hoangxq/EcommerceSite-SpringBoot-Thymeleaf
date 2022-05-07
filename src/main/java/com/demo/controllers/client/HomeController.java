package com.demo.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

	@GetMapping("/login")
	public String loginGet (Model model) {
		return "/client/login";
	}
	
	@PostMapping("/login")
	public String loginPost (Model model) {
		return "/client/login";
	}
}

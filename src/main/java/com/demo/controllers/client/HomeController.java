package com.demo.controllers.client;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.CategoryDTO;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;

@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String home (Model model) {
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
				.stream().map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("categoryDTOs", categoryDTOs);
		
		return "/client/home";
	}

	@GetMapping("/login")
	public String loginGet (Model model) {
		return "/client/login";
	}
	
	@PostMapping("/login")
	public String loginPost (Model model) {
		
		return "/client/login";
	}
}

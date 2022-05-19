package com.demo.controllers.client;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.DTO.ProductDTO;
import com.demo.DTO.UserDTO;
import com.demo.models.ERole;
import com.demo.models.Role;
import com.demo.models.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.CategoryDTO;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String home (Model model, HttpSession session) {
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
				.stream().map(e -> e.toDTO()).collect(Collectors.toList());

		List<ProductDTO> productDTOS = productService.getAllProduct().stream()
						.map(e -> e.toDTO()).collect(Collectors.toList());

		Collections.shuffle(productDTOS);
		
		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("productDTOs", productDTOS);


		return "/client/home";
	}

	@GetMapping("/login")
	public String loginGet (Model model) {

		UserDTO userDTO = new UserDTO();

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
				.stream().map(e -> e.toDTO()).collect(Collectors.toList());

		List<ProductDTO> productDTOS = productService.getAllProduct().stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());

		model.addAttribute("userDTO", userDTO);
		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("productDTOs", productDTOS);

		return "/client/login.html";
	}
	
	@PostMapping("/login")
	public String loginPost (Model model, HttpSession session,
							 @ModelAttribute(name = "userDTO") UserDTO userDTO) {

		User user = userService.getUserByEmail(userDTO.getEmail());

		if (user.getRole().getName().equals(ERole.ROLE_USER) && new BCryptPasswordEncoder().matches(userDTO.getPassword(), user.getPassword())){
			session.setAttribute("username", user.getUsername());
			return "redirect:/";
		}

		model.addAttribute("error", "Username or password is invalid");
		return "/client/login";

	}

	@GetMapping("/register")
	public String registerGet (Model model) {

		UserDTO userDTO = new UserDTO();

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
				.stream().map(e -> e.toDTO()).collect(Collectors.toList());

		List<ProductDTO> productDTOS = productService.getAllProduct().stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());

		model.addAttribute("userDTO", userDTO);
		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("productDTOs", productDTOS);

		return "/client/register";
	}

	@PostMapping("/register")
	public String registerPost (Model model, @ModelAttribute(name = "userDTO") UserDTO userDTO) {

		userDTO.setRoleDTO((new Role(ERole.ROLE_USER)).toDTO());
		userService.createUser(userDTO.toModel());

		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session){

		session.removeAttribute("username");

		return "redirect:/";
	}
}

package com.demo.controllers.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.RoleDTO;
import com.demo.DTO.UserDTO;
import com.demo.models.ERole;
import com.demo.models.Role;
import com.demo.models.User;
import com.demo.service.impl.RoleService;
import com.demo.service.CartService;
import com.demo.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping("")
	public String listUser(Model model) {

		List<User> users = userService.getAllUser();

		List<UserDTO> userDTOs = users.stream().map(e -> e.toDTO()).collect(Collectors.toList());

		model.addAttribute("userDTOs", userDTOs);

		return "admin/user/users";
	}

	@GetMapping("/add-user")
	public String addNewUserGet(Model model) {

		UserDTO userDTO = new UserDTO();
		model.addAttribute("userDTO", userDTO);

		return "admin/user/add-user";
	}

	@PostMapping("/add-user")
	public String addNewUserPost(Model model, @ModelAttribute(name = "userDTO") UserDTO userDTO) {

		User user0 = userService.getUserByUsername(userDTO.getUsername());
		User user1 = userService.getUserByEmail(userDTO.getEmail());
		
		if (user0 != null || user1 != null) {
			model.addAttribute("error", "Username or Email is existed in System");
			return "/admin/user/add-user";
		}
		
		userService.createUser(userDTO.toModel());
		
		return "redirect:/admin/users";
	}

	@GetMapping("/edit-user/{id}")
	public String editUserGet(Model model, @PathVariable(name = "id") Long id) {

		UserDTO userDTO = userService.getUserById(id).toDTO();
		
		model.addAttribute("userDTO", userDTO);

		return "admin/user/edit-user";
	}

	@PostMapping("/edit-user/{id}")
	public String editUserPost(Model model, @ModelAttribute(name = "userDTO") UserDTO userDTO,
			@PathVariable(name = "id") Long id) {
		
		User user0 = userService.getUserByUsername(userDTO.getUsername());
		User user1 = userService.getUserByEmail(userDTO.getEmail());
		
		if (user0 != null || user1 != null) {
			model.addAttribute("error", "Username or Email is existed in System");
			return "/admin/user/edit-user";
		}
		
		
		userDTO.setPassword(userService.getUserById(id).getPassword());
		
		userService.editUser(id, userDTO.toModel());

		return "redirect:/admin/users";
	}

	@GetMapping("/delete-user/{id}")
	public String deleteUser(Model model, @PathVariable(name = "id") Long id) {

		userService.deleteUser(userService.getUserById(id));

		return "redirect:/admin/users";
	}
}

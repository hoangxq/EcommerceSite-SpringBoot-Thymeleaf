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

import com.demo.DTO.CategoryDTO;
import com.demo.models.Category;
import com.demo.service.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public String listCategory(Model model) {
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory().stream().map(e -> e.toDTO())
				.collect(Collectors.toList());

		model.addAttribute("categoryDTOs", categoryDTOs);

		return "/admin/category/categories";
	}

	@GetMapping("/add-category")
	public String addNewCategoryGet(Model model) {
		CategoryDTO categoryDTO = new CategoryDTO();

		model.addAttribute("categoryDTO", categoryDTO);
		return "/admin/category/add-category";
	}

	@PostMapping("/add-category")
	public String addNewCategoryPost(Model model, @ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO) {

		Category category = categoryService.getCategoryByName(categoryDTO.getName());

		if (category != null) {
			model.addAttribute("error", "Category is existed in System");
			return "/admin/category/add-category";
		}

		categoryService.createCategory(categoryDTO.toModel());
		return "redirect:/admin/categories";
	}

	@GetMapping("/edit-category/{id}")
	public String editCategoryGet(Model model, @PathVariable(name = "id") Long id) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("categoryDTO", category.toDTO());
		return "/admin/category/edit-category";
	}

	@PostMapping("/edit-category/{id}")
	public String editCategoryPost(Model model, @PathVariable(name = "id") Long id,
			@ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO) {

		Category category = categoryService.getCategoryByName(categoryDTO.getName());

		if (category != null) {
			model.addAttribute("error", "Category is existed in System");
			return "/admin/category/edit-category";
		}

		categoryService.editCategory(categoryDTO.toModel(), id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/delete-category/{id}")
	public String deleteCategory(Model model, @PathVariable(name = "id") Long id) {
		categoryService.deleteCategory(categoryService.getCategoryById(id));
		return "redirect:/admin/categories";
	}
}

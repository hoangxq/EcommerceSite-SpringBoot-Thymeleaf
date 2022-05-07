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
import com.demo.DTO.ProductDTO;
import com.demo.models.Category;
import com.demo.models.Product;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
	
	@Autowired
	private ProductService ProductService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public String listProduct (Model model) {
		List<ProductDTO> productDTOs = ProductService.getAllProduct().stream()
				.map(e -> e.toDTO()).collect(Collectors.toList()); 
		
		model.addAttribute("productDTOs", productDTOs);
		
		return "/admin/product/products";
	}
	
	@GetMapping("/add-product")
	public String addNewProductGet (Model model) {
		ProductDTO productDTO = new ProductDTO();
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
				.stream().map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("productDTO", productDTO);
		return "/admin/product/add-product";
	}
	
	@PostMapping("/add-product")
	public String addNewProductPost (Model model, 
			@ModelAttribute(name = "productDTO") ProductDTO productDTO) {
		
		ProductService.createProduct(productDTO.toModel());
		return "redirect:/admin/products";
	}
	
	@GetMapping("/edit-product/{id}")
	public String editProductGet (Model model, 
			@PathVariable(name = "id") Long id) {
		Product product = ProductService.getProductById(id);
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
				.stream().map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("productDTO", product.toDTO());
		return "/admin/product/edit-product";
	}
	
	@PostMapping("/edit-product/{id}")
	public String editProductPost (Model model, 
			@PathVariable(name = "id") Long id, 
			@ModelAttribute(name = "productDTO") ProductDTO productDTO) {
		ProductService.editProduct(productDTO.toModel(), id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/delete-product/{id}")
	public String deleteProduct (Model model, 
			@PathVariable(name = "id") Long id) {
		ProductService.deleteProduct(ProductService.getProductById(id));
		return "redirect:/admin/products";
	}
}

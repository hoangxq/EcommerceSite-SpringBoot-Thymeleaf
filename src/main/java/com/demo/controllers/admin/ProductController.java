package com.demo.controllers.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String redirect() {
		return "redirect:/admin/products/page/1";
	}

	@GetMapping("page/{id}")
	public String listProduct(Model model, @PathVariable("id") Integer id) {
		List<ProductDTO> listAllProductDTO = ProductService.getAllProduct().stream().map(e -> e.toDTO())
				.collect(Collectors.toList());

		int pageNumber = listAllProductDTO.size() / 8;

		List<List<ProductDTO>> listPage = new ArrayList<List<ProductDTO>>();
		int index = 0;

		for (int i = 0; i < pageNumber; i++) {
			List<ProductDTO> res = listAllProductDTO.subList(index, index + 8);
			listPage.add(res);
			index += 8;
		}

		model.addAttribute("pageNumbers", pageNumber);
		model.addAttribute("productDTOs", listPage.get(id - 1));

		return "/admin/product/products";
	}

	@GetMapping("/add-product")
	public String addNewProductGet(Model model) {
		ProductDTO productDTO = new ProductDTO();
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory().stream().map(e -> e.toDTO())
				.collect(Collectors.toList());

		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("productDTO", productDTO);
		return "/admin/product/add-product";
	}

	@PostMapping("/add-product")
	public String addNewProductPost(Model model, @ModelAttribute(name = "productDTO") ProductDTO productDTO,
			@RequestParam("pictureFile") MultipartFile pictureFile) {
		
		if (pictureFile.isEmpty()) {
			model.addAttribute("error", "Please upload picture file");
			return "redirect:/admin/products/add-product";
		}

		String fileName = StringUtils.cleanPath(pictureFile.getOriginalFilename());

		String UPLOAD_DIR = "C:\\Users\\holme\\OneDrive\\Desktop\\Java_Web\\EcommerceSite-SpringBoot-Thymeleaf\\src\\main\\resources\\static\\image\\";

		try {
			Path path = Paths.get(UPLOAD_DIR + fileName);
			Files.copy(pictureFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		productDTO.setPicture("\\image\\" + fileName);

		ProductService.createProduct(productDTO.toModel());
		return "redirect:/admin/products";
	}

	@GetMapping("/edit-product/{id}")
	public String editProductGet(Model model, @PathVariable(name = "id") Long id) {
		Product product = ProductService.getProductById(id);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory().stream().map(e -> e.toDTO())
				.collect(Collectors.toList());

		model.addAttribute("categoryDTOs", categoryDTOs);
		model.addAttribute("productDTO", product.toDTO());
		return "/admin/product/edit-product";
	}

	@PostMapping("/edit-product/{id}")
	public String editProductPost(Model model, @PathVariable(name = "id") Long id,
			@ModelAttribute(name = "productDTO") ProductDTO productDTO,
			@RequestParam("pictureFile") MultipartFile pictureFile) {

		if (pictureFile.isEmpty()) {
			Product product = ProductService.getProductById(id);
			productDTO.setPicture(product.getPicture());
		}

		else {
			String fileName = StringUtils.cleanPath(pictureFile.getOriginalFilename());


			String UPLOAD_DIR = "C:\\Users\\holme\\OneDrive\\Desktop\\Java_Web\\EcommerceSite-SpringBoot-Thymeleaf\\src\\main\\resources\\static\\image\\";

			try {
				Path path = Paths.get(UPLOAD_DIR + fileName);
				Files.copy(pictureFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}

			productDTO.setPicture("\\image\\" + fileName);
		}

		ProductService.editProduct(productDTO.toModel(), id);
		return "redirect:/admin/products";
	}

	@GetMapping("/delete-product/{id}")
	public String deleteProduct(Model model, @PathVariable(name = "id") Long id) {
		ProductService.deleteProduct(ProductService.getProductById(id));
		return "redirect:/admin/products";
	}

	@GetMapping("search")
	public String searchProduct(Model model,
			@RequestParam(value = "productName", required = false) String productName) {

		List<ProductDTO> productDTOs = ProductService.getAllProduct().stream().map(e -> e.toDTO())
				.collect(Collectors.toList()).stream()
				.filter(e -> e.getName().toLowerCase().contains(productName.toLowerCase()))
				.collect(Collectors.toList());

		model.addAttribute("productDTOs", productDTOs);

		return "/admin/product/products";
	}
}

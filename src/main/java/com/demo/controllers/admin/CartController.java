package com.demo.controllers.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.DTO.CartDTO;
import com.demo.DTO.CartItemDTO;
import com.demo.DTO.ProductDTO;
import com.demo.models.CartItem;
import com.demo.models.Product;
import com.demo.service.CartItemService;
import com.demo.service.CartService;
import com.demo.service.ProductService;

@Controller
@RequestMapping("/admin/carts")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String listCart (Model model) {
		List<CartDTO> cartDTOs = cartService.getAllCart().stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("cartDTOs", cartDTOs);
		
		return "/admin/cart/carts";
	}
	
	@GetMapping("/cart-item/{id}")
	public String listItemOfCart (Model model, 
			@PathVariable(name = "id") Long id) {
		
		List<CartItem> cartItems = cartItemService.getItemOfCart(
						cartService.getCartById(id)); 
		List<CartItemDTO> cartItemDTOs = cartItems.stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		List<Product> products = productService.getAllProduct();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		
		for (Product i : products) {
			int check = 1;
			for (CartItem j : cartItems) {
				if (j.getProduct().getId().equals(i.getId())) {
					check = 0;
					break;
				}
			}
			if (check == 1)
				productDTOs.add(i.toDTO());
		}
		
		CartItemDTO cartItemDTO = new CartItemDTO();
		cartItemDTO.setQuantity(1L);
		
		model.addAttribute("cartItemDTO", cartItemDTO);
		model.addAttribute("cartItemDTOs", cartItemDTOs);
		model.addAttribute("cartDTO", cartService.getCartById(id).toDTO());
		model.addAttribute("productDTOs", productDTOs);
		
		return "/admin/cart/items";
	}
	
	@PostMapping("/cart-item/{id}")
	public String addItemOfCart (Model model, 
			@PathVariable(name = "id") Long id
			, @ModelAttribute(name = "cartItemDTO") CartItemDTO cartItemDTO) {
		
		cartItemDTO.setCartDTO(cartService.getCartById(id).toDTO());
		cartItemDTO.setProductDTO(productService
				.getProductById(cartItemDTO.getProductDTO().getId()).toDTO());
		cartItemService.createCartItem(cartItemDTO.toModel());
		
		return "redirect:/admin/carts/cart-item/{id}";
	}
	
	@GetMapping("/delete-cart-item/{id}")
	public String deleteItemOfCart (Model model,
			RedirectAttributes redirectAttributes, 
			@PathVariable(name = "id") Long id) {
		
		CartItem cartItem = cartItemService.getCartItemById(id);
		CartDTO cartDTO = cartItem.toDTO().getCartDTO();
		 
		redirectAttributes.addAttribute("id", cartDTO.getId());
		
		cartItemService.deleteCartItem(cartItem);
		
		return "redirect:/admin/carts/cart-item/{id}";
	}
}

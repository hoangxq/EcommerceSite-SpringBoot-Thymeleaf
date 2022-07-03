package com.demo.controllers.client;

import com.demo.DTO.CartDTO;
import com.demo.DTO.CartItemDTO;
import com.demo.DTO.CategoryDTO;
import com.demo.DTO.ProductDTO;
import com.demo.models.Cart;
import com.demo.models.CartItem;
import com.demo.models.Product;
import com.demo.models.User;
import com.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class ClientCartController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/my-cart")
    public String getCartDetail (Model model, HttpSession session) {

        User user = userService.getUserByUsername((String) session.getAttribute("username"));
        
        if (user == null)
        	return "redirect:/login";
        
        Cart cart = cartService.getCartByUser(user);

        List<CartItemDTO> cartItemDTOS = cartItemService.getItemOfCart(cart).stream()
                .map(e -> e.toDTO()).collect(Collectors.toList());

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
                .stream().map(e -> e.toDTO()).collect(Collectors.toList());

        List<ProductDTO> productDTOS = productService.getAllProduct().stream()
                .map(e -> e.toDTO()).collect(Collectors.toList());

        Collections.shuffle(productDTOS);
        
        Double totalPrice = cartItemDTOS.stream()
        		.map(e -> e.getQuantity() * e.getProductDTO().getPrice())
        		.reduce(0.0, Double::sum);

        model.addAttribute("categoryDTOs", categoryDTOs);
        model.addAttribute("productDTOs", productDTOS);

        model.addAttribute("cartDTO", cart.toDTO());
        model.addAttribute("totalPriceOfCart", totalPrice);
        model.addAttribute("cartItemDTOs", cartItemDTOS);

        return "/client/cart";
    }
    
    @GetMapping("/add-to-cart")
    public String addToCart (Model model, HttpSession session, 
    			@RequestParam("id") Long id, 
    			@RequestParam("quantity") Long quantity) {

        User user = userService.getUserByUsername((String) session.getAttribute("username"));

        if (user == null)
        	return "redirect:/login";
        
        Cart cart = cartService.getCartByUser(user);
        Product product = productService.getProductById(id);
        
        CartItem cartItem = new CartItem(cart, product, 1L);
        cartItem.setQuantity(quantity);
        
        cartItemService.createCartItem(cartItem);
        

        return "redirect:/my-cart";
    }
    
    @PostMapping("/update-cart/{id}")
    public String updateCart (Model model, HttpSession session, 
    			@PathVariable("id") Long id, 
    			@RequestParam("quantity") Long quantity){
        
    	if (quantity.equals(0L))
    		cartItemService.deleteCartItem(cartItemService.getCartItemById(id));
    	else cartItemService.updateCartItem(id, quantity);

        return "redirect:/my-cart";
    }

}

package com.demo.controllers.client;

import com.demo.DTO.CartDTO;
import com.demo.DTO.CartItemDTO;
import com.demo.DTO.CategoryDTO;
import com.demo.DTO.ProductDTO;
import com.demo.models.Cart;
import com.demo.models.User;
import com.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Cart cart = cartService.getCartByUser(user);

        List<CartItemDTO> cartItemDTOS = cartItemService.getItemOfCart(cart).stream()
                .map(e -> e.toDTO()).collect(Collectors.toList());

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
                .stream().map(e -> e.toDTO()).collect(Collectors.toList());

        List<ProductDTO> productDTOS = productService.getAllProduct().stream()
                .map(e -> e.toDTO()).collect(Collectors.toList());

        Collections.shuffle(productDTOS);

        model.addAttribute("categoryDTOs", categoryDTOs);
        model.addAttribute("productDTOs", productDTOS);

        model.addAttribute("cartDTO", cart.toDTO());
        model.addAttribute("cartItemDTOs", cartItemDTOS);

        return "/client/cart";
    }

}

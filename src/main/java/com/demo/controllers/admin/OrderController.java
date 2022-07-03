package com.demo.controllers.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.DTO.CartItemDTO;
import com.demo.DTO.CategoryDTO;
import com.demo.DTO.OrderDTO;
import com.demo.DTO.OrderItemDTO;
import com.demo.DTO.ProductDTO;
import com.demo.models.Cart;
import com.demo.models.CartItem;
import com.demo.models.Order;
import com.demo.models.OrderItem;
import com.demo.models.Product;
import com.demo.models.User;
import com.demo.service.CartItemService;
import com.demo.service.CartService;
import com.demo.service.CategoryService;
import com.demo.service.OrderItemService;
import com.demo.service.OrderService;
import com.demo.service.ProductService;
import com.demo.service.UserService;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("")
    public String getOrders(Model model, HttpSession session) {
    	
    	List<OrderDTO> orderDTOs = orderService.getAllOrders().stream()
    			.map(e -> e.toDTO()).collect(Collectors.toList());

    	model.addAttribute("orderDTOs", orderDTOs);
    	
        return "/admin/order/orders";
    }
    
    @GetMapping("/order-item/{id}")
	public String listItemOfOrder (Model model, 
			@PathVariable(name = "id") Long id) {
		
    	Order order = orderService.getOrderById(id);
    	
		List<OrderItemDTO> orderItemDTOs = orderItemService.getByOrder(order).stream()
				.map(e -> e.toDTO()).collect(Collectors.toList());
		
		model.addAttribute("orderDTO", order.toDTO());
		model.addAttribute("orderItemDTOs", orderItemDTOs);
		
		return "/admin/order/items";
	}
    
    @GetMapping("/delete-order/{id}")
	public String deleteOrder (Model model, 
			@PathVariable(name = "id") Long id) {
		
    	Order order = orderService.getOrderById(id);
    	
		orderItemService.deleteOrder(order);
		
		return "redirect:/admin/orders";
	}
}

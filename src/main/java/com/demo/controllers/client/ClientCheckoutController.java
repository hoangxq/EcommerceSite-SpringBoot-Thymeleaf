package com.demo.controllers.client;

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
import com.demo.models.User;
import com.demo.service.CartItemService;
import com.demo.service.CartService;
import com.demo.service.CategoryService;
import com.demo.service.OrderItemService;
import com.demo.service.OrderService;
import com.demo.service.ProductService;
import com.demo.service.UserService;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class ClientCheckoutController {

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
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/checkout")
    public String getCheckout(Model model, HttpSession session) {
    	
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
        
        Double totalPrice = cartItemDTOS.stream().
        		map(e -> e.getQuantity() * e.getProductDTO().getPrice())
        		.reduce(0.0, Double::sum);

        Collections.shuffle(productDTOS);

        model.addAttribute("categoryDTOs", categoryDTOs);
        model.addAttribute("productDTOs", productDTOS);

        model.addAttribute("cartDTO", cart.toDTO());
        model.addAttribute("cartItemDTOs", cartItemDTOS);
        model.addAttribute("totalPrice", totalPrice);
        
        model.addAttribute("orderDTO", new OrderDTO());

    	
        return "/client/checkout";
    }
    
    @PostMapping("/order")
    public String createNewOrder(Model model, HttpSession session, 
    		@ModelAttribute("orderDTO") OrderDTO orderDTO) {
    	
    	User user = userService.getUserByUsername((String) session.getAttribute("username"));
        Cart cart = cartService.getCartByUser(user);
        
        List<CartItem> cartItems = cartItemService.getItemOfCart(cart);
        
        Double totalPrice = cartItems.stream()
        		.map(e -> e.getQuantity() * e.getProduct().getPrice())
        		.reduce(0.0, Double::sum);
        
        orderDTO.setUserDTO(user.toDTO());
        orderDTO.setTimeOrder(new Date());
        orderDTO.setTotalPrice(totalPrice + 50000.0);
        
        Order order = orderService.createNewOrder(orderDTO.toModel());
        
        for (CartItem i : cartItems) {
        	OrderItem orderItem = new OrderItem(order, i.getProduct(), i.getQuantity());
        	orderItemService.createOrderItem(orderItem);
        	cartItemService.deleteCartItem(i);
        }
        
        List<OrderItemDTO> orderItemDTOs = orderItemService.getByOrder(order).stream()
        		.map(e -> e.toDTO()).collect(Collectors.toList());
        
        
        
        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("orderItemDTOs", orderItemDTOs);
        
    	
        return "redirect:/my-order";
    }
    
    @GetMapping("/my-order")
    public String listOrder(Model model, HttpSession session) {
    	
    	User user = userService.getUserByUsername((String) session.getAttribute("username"));

        if (user == null)
        	return "redirect:/login";
        
        
        List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
                .stream().map(e -> e.toDTO()).collect(Collectors.toList());
        
        List<OrderDTO> orderDTOs = orderService.getListOrderOfUser(user).stream()
        		.map(e -> e.toDTO()).collect(Collectors.toList());

        model.addAttribute("categoryDTOs", categoryDTOs);
        model.addAttribute("orderDTOs", orderDTOs);

        return "/client/list-order";
    }
    
    @GetMapping("/my-order/detail/{id}")
    public String orderDetail(Model model, HttpSession session, 
    		@PathVariable("id") Long id) {
    	
    	User user = userService.getUserByUsername((String) session.getAttribute("username"));

        if (user == null)
        	return "redirect:/login";
        
        
        Order order = orderService.getOrderById(id);
        
        List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
                .stream().map(e -> e.toDTO()).collect(Collectors.toList());
        
        List<OrderItemDTO> orderItemDTOs = orderItemService.getByOrder(order).stream()
        		.map(e -> e.toDTO()).collect(Collectors.toList());

        model.addAttribute("categoryDTOs", categoryDTOs);
        model.addAttribute("orderDTO", order.toDTO());
        model.addAttribute("orderItemDTOs", orderItemDTOs);

        return "/client/order-detail";
    }
    
}

package com.demo.service;

import java.util.List;

import com.demo.models.Order;
import com.demo.models.User;

public interface OrderService {
	
	Order createNewOrder (Order order);
	
	Order getOrderById (Long id);
	
	List<Order> getListOrderOfUser(User user);
	
	List<Order> getAllOrders();

}

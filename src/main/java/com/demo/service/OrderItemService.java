package com.demo.service;

import java.util.List;

import com.demo.models.Order;
import com.demo.models.OrderItem;

public interface OrderItemService {

	OrderItem createOrderItem (OrderItem orderItem);
	
	List<OrderItem> getByOrder (Order order);
	
	void deleteOrder (Order order);
	
}

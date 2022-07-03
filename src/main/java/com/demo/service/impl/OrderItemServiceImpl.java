package com.demo.service.impl;

import com.demo.repository.OrderItemRepository;
import com.demo.repository.OrderRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Order;
import com.demo.models.OrderItem;
import com.demo.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		Order order = orderRepository.findById(orderItem.getOrder().getId()).get();
		orderItem.setOrder(order);
		return orderItemRepository.save(orderItem);
	}

	@Override
	public List<OrderItem> getByOrder(Order order) {
		return orderItemRepository.findByOrder(order);
	}

	@Override
	public void deleteOrder(Order order) {
		orderItemRepository.deleteByOrder(order.getId());
		orderRepository.delete(order);
	}

}

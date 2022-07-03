package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Order;
import com.demo.models.User;
import com.demo.repository.OrderRepository;
import com.demo.repository.UserRepository;
import com.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Order createNewOrder(Order order) {
		User user = userRepository.findByUsername(order.getUser().getUsername());
		order.setUser(user);
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getListOrderOfUser(User user) {
		return orderRepository.findByUser(user);
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

}

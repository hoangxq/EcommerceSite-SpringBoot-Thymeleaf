package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Order;
import com.demo.models.User;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUser(User user);
	
}

package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}

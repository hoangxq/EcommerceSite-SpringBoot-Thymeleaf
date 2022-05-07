package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}

package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}

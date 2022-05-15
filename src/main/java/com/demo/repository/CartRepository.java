package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Cart;
import com.demo.models.User;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart getCartByUser (User user);
	
}

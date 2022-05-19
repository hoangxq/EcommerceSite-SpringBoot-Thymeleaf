package com.demo.service;

import java.util.List;

import com.demo.models.Cart;
import com.demo.models.User;

public interface CartService {

	List<Cart> getAllCart ();
	
	Cart getCartById(Long id);
	
	Cart getCartByUser(User user);

	//Cart getCartByUsername(String username);
	
	Cart createCart (Cart Cart);
	
	void deleteCart (Cart cart);
	
}

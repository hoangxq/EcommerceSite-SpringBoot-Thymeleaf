package com.demo.service;

import java.util.List;

import com.demo.models.Cart;

public interface CartService {

	List<Cart> getAllCart ();
	
	Cart getCartById(Long id);
	
	Cart createCart (Cart Cart);
	
	Cart editCart (Cart Cart, Long id);
	
	void deleteCart (Cart Cart);
	
}

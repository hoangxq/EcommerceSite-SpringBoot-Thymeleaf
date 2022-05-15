package com.demo.service;

import java.util.List;

import com.demo.models.Cart;
import com.demo.models.CartItem;

public interface CartItemService {

	List<CartItem> getItemOfCart(Cart cart);
	
	CartItem createCartItem(CartItem cartItem);
	
	CartItem getCartItemById(Long id);
	
	CartItem updateCartItem(Long id, Long quantity);
	
	void deleteCartItem (CartItem cartItem);
	
}

package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Cart;
import com.demo.models.User;
import com.demo.repository.CartRepository;
import com.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> getAllCart() {
		return cartRepository.findAll();
	}

	@Override
	public Cart getCartById(Long id) {
		return cartRepository.findById(id).get();
	}

	@Override
	public Cart createCart(Cart Cart) {
		return cartRepository.save(Cart);
	}

	@Override
	public Cart getCartByUser(User user) {
		return cartRepository.getCartByUser(user);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepository.delete(cart);
	}

}

package com.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Cart;
import com.demo.models.CartItem;
import com.demo.models.Product;
import com.demo.repository.CartItemRepository;
import com.demo.service.CartItemService;
import com.demo.service.CartService;
import com.demo.service.ProductService;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;

	@Override
	public List<CartItem> getItemOfCart(Cart cart) {
		List<CartItem> cartItems = cartItemRepository.findAll();
		return cartItems.stream()
				.filter(e -> e.getCart().getId().equals(cart.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		Cart cart = cartService.getCartById(cartItem.getCart().getId());
		Product product = productService.getProductById(cartItem.getProduct().getId());
		cartItem.setCart(cart);
		cartItem.setProduct(product);
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem getCartItemById(Long id) {
		return cartItemRepository.findById(id).get();
	}

	@Override
	public CartItem updateCartItem(Long id, Long quantity) {
		CartItem cartItem = cartItemRepository.findById(id).get();
		cartItem.setQuantity(quantity);
		return cartItemRepository.save(cartItem);
	}

	@Override
	public void deleteCartItem(CartItem cartItem) {
		cartItemRepository.delete(cartItem);
	}

}

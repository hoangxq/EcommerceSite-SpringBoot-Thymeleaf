package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.CartItemDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private Long quantity;

	public CartItem(Cart cart, Product product, Long quantity) {
		super();
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}
	
	public CartItemDTO toDTO () {
		return new CartItemDTO(id, cart.toDTO(), product.toDTO(), quantity);
	}

}

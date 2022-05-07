package com.demo.DTO;

import com.demo.models.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

	private Long id;
	private CartDTO cartDTO;
	private ProductDTO productDTO;
	private Long quantity;

	public CartItem toModel () {
		return new CartItem(id, cartDTO.toModel(), productDTO.toModel(), quantity);
	}
	
}

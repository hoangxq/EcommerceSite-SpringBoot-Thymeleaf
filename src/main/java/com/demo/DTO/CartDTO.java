package com.demo.DTO;

import com.demo.models.Cart;
import com.demo.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

	private Long id;
	private UserDTO userDTO;

	public Cart toModel () {
		return new Cart(id, userDTO.toModel());
	}
	
}

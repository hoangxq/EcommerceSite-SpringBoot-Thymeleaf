package com.demo.DTO;

import com.demo.models.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

	private Long id;
	private OrderDTO orderDTO;
	private ProductDTO productDTO;
	private Long quantity;

	public OrderItem toModel () {
		return new OrderItem(id, orderDTO.toModel(), productDTO.toModel()
				, quantity);
	}
}

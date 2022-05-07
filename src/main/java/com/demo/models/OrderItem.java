package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.OrderItemDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oser_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private Long quantity;

	public OrderItem(Order order, Product product, Long quantity) {
		super();
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}
	
	public OrderItemDTO toDTO () {
		return new OrderItemDTO(id, order.toDTO(), product.toDTO(), quantity);
	}

}

package com.demo.models;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.demo.DTO.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "receiver_name")
	private String receiverFullname;
	
	@Column(name = "receiver_address")
	private String receiverAddress;
	
	@Column(name = "receiver_phone")
	private String receiverPhone;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "time_order")
	private Date timeOrder;
	
	@Column(name = "total_price")
	private Double totalPrice;

	public Order(User user, String receiverFullname, String receiverAddress, String receiverPhone, Date timeOrder) {
		super();
		this.user = user;
		this.receiverFullname = receiverFullname;
		this.receiverAddress = receiverAddress;
		this.receiverPhone = receiverPhone;
		this.timeOrder = timeOrder;
	}
	
	public OrderDTO toDTO () {
		return new OrderDTO(id, user.toDTO(), receiverFullname
				, receiverAddress, receiverPhone, timeOrder, totalPrice);
	}
}

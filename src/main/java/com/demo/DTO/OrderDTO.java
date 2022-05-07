package com.demo.DTO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.demo.models.Order;
import com.demo.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Long id;
	private UserDTO userDTO;
	private String receiverFullname;
	private String receiverAddress;
	private String receiverPhone;
	
	@DateTimeFormat(pattern = "dd/MM/yyy")
	private Date timeOrder;

	public Order toModel () {
		return new Order(id, userDTO.toModel(), receiverFullname, receiverAddress
				, receiverPhone, timeOrder);
	}
}

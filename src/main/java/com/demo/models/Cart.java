package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Cart(User user) {
		super();
		this.user = user;
	}
	
	public CartDTO toDTO() {
		return new CartDTO(id, user.toDTO());
	}

}

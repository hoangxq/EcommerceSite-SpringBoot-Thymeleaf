package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.RoleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ERole name;

	public Role(ERole name) {
		super();
		this.name = name;
	}
	
	public RoleDTO toDTO () {
		return new RoleDTO(id, name.toString()); 
	}
	
}

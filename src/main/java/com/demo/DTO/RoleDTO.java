package com.demo.DTO;

import lombok.NoArgsConstructor;

import com.demo.models.ERole;
import com.demo.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

	private Long id;
	private String name;
	
	public Role toModel () {
		return new Role(id, ERole.valueOf(name));
	}
}

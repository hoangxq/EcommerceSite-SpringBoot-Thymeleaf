package com.demo.service;

import java.util.List;

import com.demo.models.ERole;
import com.demo.models.Role;

public interface RoleServiceImpl {

	List<Role> getAllRole();

	Role getRoleById(Long id);
	
	Role getRoleByName (ERole name);

}

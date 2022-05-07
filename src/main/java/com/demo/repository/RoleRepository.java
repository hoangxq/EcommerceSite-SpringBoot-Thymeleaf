package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.ERole;
import com.demo.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(ERole name);	
	
}

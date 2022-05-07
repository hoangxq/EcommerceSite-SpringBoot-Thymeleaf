package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.ERole;
import com.demo.models.Role;
import com.demo.repository.RoleRepository;
import com.demo.service.RoleServiceImpl;

@Service
public class RoleService implements RoleServiceImpl{

	@Autowired
	private RoleRepository RoleRepository;

	@Override
	public List<Role> getAllRole() {
		return RoleRepository.findAll();
	}

	@Override
	public Role getRoleById(Long id) {
		return RoleRepository.findById(id).get();
	}

	@Override
	public Role getRoleByName(ERole name) {
		return RoleRepository.findByName(name);
	}
	
	
	
}

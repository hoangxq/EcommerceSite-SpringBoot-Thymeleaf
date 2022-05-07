package com.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.DTO.UserRegistrationDTO;
import com.demo.models.User;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDTO registrationDTO);
	
	User getUserById (Long id);
	
	List<User> getAllUser ();
	
	User editUser (Long id, User user);
	
	void deleteUser (User user);
	
}

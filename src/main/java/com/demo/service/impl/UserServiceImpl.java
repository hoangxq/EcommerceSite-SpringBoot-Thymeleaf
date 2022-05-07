package com.demo.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.DTO.UserRegistrationDTO;
import com.demo.models.ERole;
import com.demo.models.Role;
import com.demo.models.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(UserRegistrationDTO registrationDTO) {
		User user = new User(registrationDTO.getUsername(), registrationDTO.getEmail(), 
				new BCryptPasswordEncoder().encode(registrationDTO.getPassword()), 
				new Role(ERole.ROLE_ADMIN));
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		
		if (user == null)
			throw new UsernameNotFoundException("Invalid Username or Password");
		
		return new org.springframework.security.core.userdetails.User(
					user.getEmail(), user.getPassword(), 
					mapRolesToAuthorities(new HashSet<Role>(Arrays.asList(user.getRole())))
				);
		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		
		return roles.stream().map(role -> 
			new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
		
	}

	
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	
	@Override
	public User editUser(Long id, User user) {
		return null;
	}

	
	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}

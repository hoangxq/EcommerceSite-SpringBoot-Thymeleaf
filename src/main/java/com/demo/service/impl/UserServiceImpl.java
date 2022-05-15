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
import com.demo.models.Cart;
import com.demo.models.CartItem;
import com.demo.models.ERole;
import com.demo.models.Role;
import com.demo.models.User;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;
import com.demo.service.CartItemService;
import com.demo.service.CartService;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
//	@Override
//	public User save(UserRegistrationDTO registrationDTO) {
//		User user = new User(registrationDTO.getUsername(), registrationDTO.getEmail(), 
//				new BCryptPasswordEncoder().encode(registrationDTO.getPassword()), 
//				new Role(ERole.ROLE_ADMIN));
//		
//		return userRepository.save(user);
//	}

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
		User userInDB = userRepository.findById(id).get();
		Role role = roleService.getRoleByName(user.getRole().getName());
		
		userInDB.setEmail(user.getEmail());
		userInDB.setUsername(user.getUsername());
		userInDB.setRole(role);
		return userRepository.save(userInDB);
	}

	
	@Override
	public void deleteUser(User user) {
		
		if (user.getRole().getName().equals(ERole.ROLE_USER)) {
			Cart cart = cartService.getCartByUser(user);
			List<CartItem> cartItems = cartItemService.getItemOfCart(cart);
			for (CartItem i : cartItems) {
				cartItemService.deleteCartItem(i);
			}
			cartService.deleteCart(cart);
		}
		
		userRepository.delete(user);
	}

	
	@Override
	public User createUser(User user) {
		Role role = roleService.getRoleByName(user.getRole().getName());
		user.setRole(role);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		if (user.getRole().getName().equals(ERole.ROLE_USER)) {
			cartService.createCart(new Cart(user));
		}
		return user;
	}

	
}

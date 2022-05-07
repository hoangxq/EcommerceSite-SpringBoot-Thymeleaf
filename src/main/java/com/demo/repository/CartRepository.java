package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}

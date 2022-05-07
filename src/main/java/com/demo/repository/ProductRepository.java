package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findByName(String name);
	
}

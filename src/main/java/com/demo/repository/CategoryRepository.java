package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByName(String name);
	
}

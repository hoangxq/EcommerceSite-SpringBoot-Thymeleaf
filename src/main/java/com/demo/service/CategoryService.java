package com.demo.service;

import java.util.List;

import com.demo.models.Category;

public interface CategoryService {

	List<Category> getAllCategory ();
	
	Category getCategoryById(Long id);

	Category getCategoryByName(String name);
	
	Category createCategory (Category category);
	
	Category editCategory (Category category, Long id);
	
	void deleteCategory (Category category);
	
}

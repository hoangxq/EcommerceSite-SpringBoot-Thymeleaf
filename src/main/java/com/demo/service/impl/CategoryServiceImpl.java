package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repository.CategoryRepository;

import com.demo.models.Category;
import com.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category editCategory(Category category, Long id) {
		Category categoryInDB = categoryRepository.findById(id).get();
		
		categoryInDB.setName(category.getName());
		categoryInDB.setDescription(category.getDescription());
		//categoryInDB.setList_product(category.getList_product());
		
		return categoryRepository.save(categoryInDB);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

}

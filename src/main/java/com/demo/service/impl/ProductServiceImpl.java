package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Category;
import com.demo.models.Product;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product createProduct(Product Product) {
		Category category = categoryRepository.findById(Product.getCategory().getId()).get();
		Product.setCategory(category);
		return productRepository.save(Product);
	}

	@Override
	public Product editProduct(Product Product, Long id) {
		Product productInDB = productRepository.findById(id).get();
		Category category = categoryRepository.findById(Product.getCategory().getId()).get();
		
		productInDB.setCategory(category);
		productInDB.setDescription(Product.getDescription());
		productInDB.setName(Product.getName());
		productInDB.setPicture(Product.getPicture());
		productInDB.setPrice(Product.getPrice());
		productInDB.setQuantity(Product.getQuantity());
		
		return productRepository.save(productInDB);
	}

	@Override
	public void deleteProduct(Product Product) {
		productRepository.delete(Product);
	}
	
	
	
}

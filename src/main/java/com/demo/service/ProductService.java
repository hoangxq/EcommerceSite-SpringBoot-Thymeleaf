package com.demo.service;

import java.util.List;

import com.demo.models.Product;

public interface ProductService {

	List<Product> getAllProduct();

	Product getProductById(Long id);

	Product createProduct(Product Product);

	Product editProduct(Product Product, Long id);

	void deleteProduct(Product Product);

}

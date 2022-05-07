package com.demo.DTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.demo.models.Category;
import com.demo.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private Long id;
	private String name;
	private String description;
//	private Set<ProductDTO> list_product = new HashSet<ProductDTO>();

//	public Category toModel () {
//		Set<Product> products = list_product.stream()
//				.map(e -> e.toModel()).collect(Collectors.toSet());
//		return new Category(id, name, description, products);
//	}
	
	public Category toModel () {
//		Set<Product> products = list_product.stream()
//				.map(e -> e.toModel()).collect(Collectors.toSet());
		return new Category(id, name, description);
	}
}

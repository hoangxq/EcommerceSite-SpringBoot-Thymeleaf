package com.demo.DTO;

import com.demo.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private Long quantity;
	private Double price;
	private String picture;
	private CategoryDTO categoryDTO;
	
	public Product toModel () {
		return new Product(id, name, description, quantity, price, picture, categoryDTO.toModel());
	}

}

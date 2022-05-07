package com.demo.models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.demo.DTO.CategoryDTO;
import com.demo.DTO.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;
	
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "category_products", 
//		joinColumns = @JoinColumn(name = "category_id"),
//		inverseJoinColumns = @JoinColumn(name = "product_id"))
//	private Set<Product> list_product = new HashSet<Product>();

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

//	public Category(String name, String description, Set<Product> list_product) {
//		super();
//		this.name = name;
//		this.description = description;
//		this.list_product = list_product;
//	}
	
//	public CategoryDTO toDTO () {
//		Set<ProductDTO> productDTOs = list_product.stream()
//				.map(e -> e.toDTO()).collect(Collectors.toSet());
//		return new CategoryDTO(id, name, description, productDTOs);
//	}
	
	public CategoryDTO toDTO () {
//		Set<ProductDTO> productDTOs = list_product.stream()
//				.map(e -> e.toDTO()).collect(Collectors.toSet());
		return new CategoryDTO(id, name, description);
	}
	
}

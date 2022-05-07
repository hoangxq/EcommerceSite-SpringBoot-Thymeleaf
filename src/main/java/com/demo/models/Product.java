package com.demo.models;

import javax.persistence.*;

import com.demo.DTO.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;
	private Long quantity;
	private Double price;	
	private String picture;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Product(String name, String description, Long quantity, Double price, String picture, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.picture = picture;
		this.category = category;
	}
	
	public ProductDTO toDTO () {
		return new ProductDTO(id, name, description, quantity, price, picture, category.toDTO());
	}

}

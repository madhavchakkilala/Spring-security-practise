package com.store.online.Ecom.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product implements Serializable{
	@Id
	private String id = UUID.randomUUID().toString();
	private String name;
	private Double price;
	private String description;
	private String brand;
	private String imageUrl;
	private boolean isBlocked;

	@ManyToMany
	@JoinTable(name="product_category",joinColumns = @JoinColumn(name="product_id",referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName = "id"))
	private Set<Category> categories;

	@ManyToMany
	@JoinTable(name="cart",joinColumns = @JoinColumn(name="product_id",referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName = "email"))
	private Set<User> users;
}

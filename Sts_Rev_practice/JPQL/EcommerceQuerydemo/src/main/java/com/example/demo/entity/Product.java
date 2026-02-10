package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="products",indexes= 
{@Index(name="idx_products_name",columnList="name"),
 @Index(name="idx_products_sku",columnList="sku")
})
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable=false,unique=true)
private String sku;

@Column(nullable=false)
private String name;

@Column(nullable=false)
private BigDecimal price;

@Column(nullable=false)
private int stock;


private boolean active=true;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="category_id",nullable=false)
private Category category;

public Product() {}

public Product(String sku, String name, BigDecimal price, int stock, boolean active, Category category) {
	super();
	this.sku = sku;
	this.name = name;
	this.price = price;
	this.stock = stock;
	this.active = active;
	this.category = category;
}

public Long getId() {
	return id;
}

public String getSku() {
	return sku;
}

public void setSku(String sku) {
	this.sku = sku;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public BigDecimal getPrice() {
	return price;
}

public void setPrice(BigDecimal price) {
	this.price = price;
}

public int getStock() {
	return stock;
}

public void setStock(int stock) {
	this.stock = stock;
}

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}





}

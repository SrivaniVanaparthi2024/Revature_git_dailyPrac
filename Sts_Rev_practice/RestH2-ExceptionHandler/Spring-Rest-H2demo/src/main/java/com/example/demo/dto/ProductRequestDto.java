package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1, message = "Price must be >= 1")
    private double price;

    @Min(value = 0, message = "Stock must be >= 0")
    private int stock;

   
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}

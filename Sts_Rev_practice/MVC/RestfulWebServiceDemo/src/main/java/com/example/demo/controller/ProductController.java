package com.example.demo.controller;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ProductService;
import com.example.demo.entity.Product;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService service;
	
	public ProductController(ProductService service) {
		this.service=service;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product p){
		Product saved=service.create(p);
		
		URI location=URI.create("/api/products/"+saved.getId());
		return ResponseEntity.created(location).body(saved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> upddateFull(@PathVariable Long id,@Valid @RequestBody Product p){
		return ResponseEntity.ok(service.updateFull(id, p));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Product> pathStock(@PathVariable Long id,@RequestBody Map<String,Object> body){
		int stock=(int) body.getOrDefault("stock", 0);
		return ResponseEntity.ok(service.updateStockOnly(id, stock));
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); 
    }


}

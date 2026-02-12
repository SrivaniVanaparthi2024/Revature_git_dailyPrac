package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepository;

@Service
public class ProductService {
private final ProductRepository repo;

public ProductService(ProductRepository repo) {
	this.repo=repo;
}

public List<Product> getAll(){
	return repo.findAll();
	}

public Product getById(Long id) {
	return repo.findById(id)
			.orElseThrow(()-> new RuntimeException("Product not found: "+id));
}

public Product create(Product p) {
	p.setId(null);
	return repo.save(p);
}

public Product updateFull(Long id, Product p) {
    Product existing = getById(id);
    existing.setName(p.getName());
    existing.setPrice(p.getPrice());
    existing.setStock(p.getStock());
    return repo.save(existing);
}

public Product updateStockOnly(Long id, int stock) {
    Product existing = getById(id);
    existing.setStock(stock);
    return repo.save(existing);
}

public void delete(Long id) {
    Product existing = getById(id);
    repo.delete(existing);
}

}

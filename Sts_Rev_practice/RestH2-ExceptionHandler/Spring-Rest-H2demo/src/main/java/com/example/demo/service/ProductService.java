package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repo.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
    public Product create(Product p) {
        p.setId(null); // ensure new insert
        return repo.save(p);
    }

    public Product updateFull(Long id, ProductRequestDto dto) {
        Product existing = getById(id);
        ProductMapper.copyToEntity(dto, existing);
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

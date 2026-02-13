package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.service.ProductApiService;

@Controller
@RequestMapping("/products")
public class ProductMvcController {

	private final ProductApiService api;

	public ProductMvcController(ProductApiService api) {
		this.api = api;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("products", api.getAll());
		return "products/list";

	}
	@GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        ProductResponseDto p = api.getById(id);
        model.addAttribute("p", p);
        return "products/details";
    }

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("product", new ProductRequestDto());
		return "products/new";
	}

	@PostMapping
	public String create(@Valid @ModelAttribute("product") ProductRequestDto dto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "products/new";
		}
		api.create(dto);
		return "redirect:/products";
	}

	   @GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Long id, Model model) {
		ProductResponseDto existing=api.getById(id);
		
		ProductRequestDto form=new ProductRequestDto();
		form.setName(existing.getName());
		form.setPrice(existing.getPrice());
		form.setStock(existing.getStock());
		
		model.addAttribute("id",id);
		model.addAttribute("product",form);
		return "products/edit";
	}

	@PostMapping("{id}/edit")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("product") ProductRequestDto dto,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("id", id);
			return "products/edit";
		}
		api.update(id, dto);
		return "redirect:/products";
	}
	
	@GetMapping("/{id}/stock")
	public String showStockForm(@PathVariable Long id, Model model) {
		ProductResponseDto p=api.getById(id);
		model.addAttribute("p",p);
		return "products/stock";
	}
	
	@PostMapping("/{id}/stock")
	public String updateStock(@PathVariable Long id, @RequestParam int stock)
	{
		api.updateStock(id, stock);
		return "redirect:/products";
	}
		
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id)
	{
		api.delete(id);
		return "redirect:/products";
	}
}
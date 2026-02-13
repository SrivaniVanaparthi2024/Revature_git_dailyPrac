package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.dto.ProductResponseDto;

@Service
public class ProductApiService {

	private final RestTemplate restTemplate;

	@Value("${api.base-url}")
	private String baseUrl;

	public ProductApiService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<ProductResponseDto> getAll() {
		ProductResponseDto[] arr = restTemplate.getForObject(baseUrl, ProductResponseDto[].class);
		return arr == null ? List.of() : Arrays.asList(arr);
	}

	public ProductResponseDto getById(Long id) {
		return restTemplate.getForObject(baseUrl + "/" + id, ProductResponseDto.class);
	}

	public ProductResponseDto create(ProductRequestDto dto) {
		return restTemplate.postForObject(baseUrl, dto, ProductResponseDto.class);
	}

	public ProductResponseDto update(Long id, ProductRequestDto dto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<ProductRequestDto> request = new HttpEntity<>(dto, headers);

		ResponseEntity<ProductResponseDto> response = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.PUT, request,
				ProductResponseDto.class);
		return response.getBody();
	}

	public ProductResponseDto updateStock(Long id, int stock) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Integer> payload = Map.of("stock", stock);

		HttpEntity<Map<String, Integer>> request = new HttpEntity<>(payload, headers);

		ResponseEntity<ProductResponseDto> response = restTemplate.exchange(baseUrl + "/" + id + "/stock",
				HttpMethod.PATCH, request, ProductResponseDto.class);

		return response.getBody();
	}

	public void delete(Long id) {
		restTemplate.delete(baseUrl + "/" + id);
	}

}
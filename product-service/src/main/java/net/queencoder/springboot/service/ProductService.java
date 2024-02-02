package net.queencoder.springboot.service;

import java.util.*;

import org.springframework.stereotype.Service;

import lombok.*;
import net.queencoder.springboot.dto.ProductRequest;
import net.queencoder.springboot.model.Product;
import net.queencoder.springboot.repository.ProductRepository;
import net.queencoder.springboot.dto.*;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	 
	private final ProductRepository productRepository;
	
	//Instead of using this, we will use the @RequiredArgsConstructor from Lombok. It will create the constructor during runtime
//	public ProductService(ProductRepository productRepository) {
//		super();
//		this.productRepository = productRepository;
//	}

	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		productRepository.save(product);
		
		log.info("Product {} is saved", product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
//		products.stream().map(product -> mapToProductresponse(product)).toList()
		return products.stream().map(this::mapToProductresponse).toList();
	}

	private ProductResponse mapToProductresponse(Product product) {
		
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
	
	
}

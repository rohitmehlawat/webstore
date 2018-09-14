package com.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.getAllProducts();
	}
	
	@Override
	public void updateAllStock() {
		
		List<Product> allProducts=productRepository.getAllProducts();
		for(Product product:allProducts) {
			if(product.getUnitsInStock()<500) {
				productRepository.updateStock(product.getProductId(), product.getUnitsInStock()+1000);
				
			}
			
		}
		
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.getProductsByCategory(category);
	}

	@Override
	public List<Product> getProductByFilter(Map<String, List<String>> filterParams) {
		// TODO Auto-generated method stub
		return productRepository.getProductByFilter(filterParams);
	}

	@Override
	public Product getProductsById(String productId) {
		// TODO Auto-generated method stub
		return productRepository.getProductById(productId);
	}

	@Override
	public List<Product> getProductByMultipleFilters(String category, Map<String, List<String>> filterParams,
			String brandName) {
		// TODO Auto-generated method stub
		return productRepository.getProductByMultipleFilters(category, filterParams, brandName);
	}

	

}

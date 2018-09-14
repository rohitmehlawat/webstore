package com.packt.webstore.service;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductService {

	List<Product> getAllProducts();
	
	void updateAllStock();
	
	List<Product> getProductByCategory(String category);
	
	List<Product> getProductByFilter(Map<String,List<String>> filterParams);
	
	Product getProductsById(String productId);
	
	List<Product> getProductByMultipleFilters(String category,Map<String,List<String>> filterParams,String brandName);
}

package com.packt.webstore.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductRepository {

	List<Product> getAllProducts();
	
	void updateStock(String productId,long noOfunits);
	
	List<Product> getProductsByCategory(String category);
	
	List<Product> getProductByFilter(Map<String,List<String>> filterParams);
	
	Product getProductById(String productId);
	
	List<Product> getProductByMultipleFilters(String category,Map<String,List<String>> filterParams,String brandName);
}

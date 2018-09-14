package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		Map<String, Object> params=new HashMap<>();
		List<Product> result=jdbcTemplate.query("select * from products", params,new ProductMapper());
		return result;
	}
	
	@Override
	public void updateStock(String productId,long noOfUnits) {
		String SQL="UPDATE PRODUCTS SET UNITS_IN_STOCK=:unitsInStock WHERE ID=:id";
		Map<String,Object> params=new HashMap();
		params.put("unitsInStock", noOfUnits);
		params.put("id", productId);
		jdbcTemplate.update(SQL,params);
		
	}
	
	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM PRODUCTS WHERE CATEGORY=:category";
		Map<String,Object> params=new HashMap<>();
		params.put("category", category);	
		return jdbcTemplate.query(SQL, params,new ProductMapper());
	}
	

	@Override
	public List<Product> getProductByFilter(Map<String, List<String>> filterParams) {
		// TODO Auto-generated method stub
		
		String SQL="SELECT * FROM PRODUCTS WHERE CATEGORY IN(:categories) AND "
				+ "MANUFACTURER IN (:brands)";
		return jdbcTemplate.query(SQL, filterParams,new ProductMapper());
	}
	
	@Override
	public Product getProductById(String productId) {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM PRODUCTS WHERE ID=:id";
		Map<String,Object> params=new HashMap<>();
		params.put("id", productId);
		return jdbcTemplate.queryForObject(SQL, params,new ProductMapper());
	}
	

	@Override
	public List<Product> getProductByMultipleFilters(String category, Map<String, List<String>> filterParams,
			String brandName) {
		// TODO Auto-generated method stub
		String SQL="SELECT * FROM PRODUCTS WHERE CATEGORY=:category and MANUFACTURER=:brand and UNIT_PRICE BETWEEN :low AND :high";
		Map<String,Object> params=new HashMap<>();
		params.put("category", category);
		params.put("brand", brandName);
		params.put("low", filterParams.get("low").get(0));
		params.put("high", filterParams.get("high").get(0));
		List<Product> products=jdbcTemplate.query(SQL, params,new ProductMapper());
		return products;
	}
	
	private static final class ProductMapper implements RowMapper<Product>{

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Product product=new Product();
			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("CONDITION"));
			product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));		
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			return product;
		}
		
	}




	


	

}

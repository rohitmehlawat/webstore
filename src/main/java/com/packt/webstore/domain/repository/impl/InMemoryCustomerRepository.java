package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customerList =new ArrayList<>();
		customerList.add(new Customer("1","Rohit","Delhi",10));
		customerList.add(new Customer("2","Raman","Delhi",20));
		
		return customerList;
	}

	
}

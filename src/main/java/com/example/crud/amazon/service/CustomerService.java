package com.example.crud.amazon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.crud.amazon.entity.Customer;
import com.example.crud.amazon.exception.CustomerNotFoundException;
import com.example.crud.amazon.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository cusRepo;
	
	// First Service
	public Customer createCustomer(Customer cus) // User-defined Method
	{
		return cusRepo.save(cus);
	}
	
	// Second Service
	public ResponseEntity<?>  getAllCustomer()
	{
		List<Customer> cus = cusRepo.findAll();
		if(cus.isEmpty()) // true
		{
			return ResponseEntity.ok("No Customer details Found");
		}
		return ResponseEntity.ok("Customer details displayed");	
	}
	
	// Third Service
	public Optional<Customer> getCustomerById(Long id)
	{
		return cusRepo.findById(id);
	}
	
	// Fourth Service 
	public Customer updateCustomer(Long id,Customer cus) throws CustomerNotFoundException
	{
		Customer c = cusRepo.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found")); // Check id is available or not
		c.setFirstName(cus.getFirstName());
		c.setLastName(cus.getLastName());
		c.setEmail(cus.getEmail());
		c.setCity(cus.getCity());
		c.setState(cus.getState());
		return cusRepo.save(c);
	}
	// Fifth Service
	public void deleteCustomer(Long id)
	{
		 	cusRepo.deleteById(id);
	}
	
	

}

package com.example.crud.amazon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.amazon.entity.Customer;
import com.example.crud.amazon.exception.CustomerNotFoundException;
import com.example.crud.amazon.service.CustomerService;

@RestController
@RequestMapping("/api/amazon")
public class CustomerController {
	
	@Autowired
	private CustomerService cusService;
	
	// PostMapping for create Customer
	@PostMapping
	public String addCustomer(@RequestBody Customer cus) 
	{
	    try 
	    {
	        boolean result = cusService.createCustomer(cus);

	        if (result) 
	        {
	            return "Customer record saved successfully..!";
	        } 
	        else 
	        {
	            return "Customer record not saved..!";
	        }
	    } 
	    catch (Exception e) 
	    {
	        return "Customer record not saved..!\n" + e.getMessage();
	    }
	}
	
	// GetMapping for read all customer
	@GetMapping("/getAll")
	List<Customer> readAllCustomer()
	{
		return cusService.getAllCustomer();
	}
	
	// GetMapping for read customer by using ID 
	@GetMapping("/get/{id}")
	public Customer getCustomerById(@PathVariable Long id) throws CustomerNotFoundException
	{
		return cusService.getCustomerById(id).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
	}
	
	// PutMapping for update or edit by using ID
	@PutMapping("/update/{id}")
	public Customer editCustomerById(@PathVariable Long id,@RequestBody Customer cus) throws CustomerNotFoundException
	{
		return cusService.updateCustomer(id, cus);
	}
	
	// DeleteMapping for deletion by using ID
	@DeleteMapping("/delete/{id}")
	public void removeCustomer(@PathVariable Long id)
	{
		 cusService.deleteCustomer(id);
	}

}

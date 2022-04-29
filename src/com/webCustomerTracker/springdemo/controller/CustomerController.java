package com.webCustomerTracker.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webCustomerTracker.springdemo.constants.SortUtils;
import com.webCustomerTracker.springdemo.entity.Customer;
import com.webCustomerTracker.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject the CustomerService
	@Autowired
	private CustomerService service;

	@GetMapping("/list")
	public String listCustomers(Model model, @RequestParam(name = "sort", required = false) String sort) {
		
		List<Customer> customers= null;
		
		if (sort != null) {
			int sortField = Integer.parseInt(sort);
			
			//get customers from dao
			customers = service.getCustomers(sortField);
		}
		else {
			customers = service.getCustomers(SortUtils.LAST_NAME);
		}
		
		//add those customers to the model
		model.addAttribute("customers", customers);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer customer= new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		//save the customer using out service
		service.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerid, Model model ) {
		
		//get the customer from db
		Customer customer = service.getCustomer(customerid);
		System.out.println(customer);
		//set this customer as a modelAttribute to pre-populate the form
		model.addAttribute("customer", customer);
		
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerID) {
		service.deleteCustomer(customerID);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("searchName") String searchName, Model model) {
		
		List<Customer> customers = service.searchCustomers(searchName);
		
		model.addAttribute("customers" ,customers);
		
		return "list-customers";
	}
	
	
}

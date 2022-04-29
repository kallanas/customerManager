package com.webCustomerTracker.springdemo.service;

import java.util.List;

import com.webCustomerTracker.springdemo.entity.Customer;

public interface CustomerService {
	List<Customer> getCustomers(int sortField);

	void saveCustomer(Customer customer);

	Customer getCustomer(int id);

	void deleteCustomer(int id);

	List<Customer> searchCustomers(String searchName);
}

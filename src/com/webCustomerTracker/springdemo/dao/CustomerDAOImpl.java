package com.webCustomerTracker.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webCustomerTracker.springdemo.constants.SortUtils;
import com.webCustomerTracker.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the sessionFactory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(int sortField) {
		//get the currentSession
		Session session = sessionFactory.getCurrentSession();
		
		String fieldName = null;
		
		switch (sortField) {
			case SortUtils.FIRST_NAME: 
				fieldName = "firstName";
				break;
			case SortUtils.LAST_NAME: 
				fieldName = "lastName";
				break;
			case SortUtils.EMAIL: 
				fieldName = "email";
				break;
			default:
				fieldName = "lastName";
		}
		
		//create a query..
		Query<Customer> query = session.createQuery("from Customer order by " + fieldName, 
				Customer.class);

		//execute query
		List<Customer> customers = query.getResultList();
		//return the list of customers that I retrieve
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
				
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query =  session.createQuery("delete from Customer where id=:customerID");
		query.setParameter("customerID", id);
		query.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("1111111111111111111111" + searchName);
		Query<Customer> query = null;
		if (searchName != null && searchName.trim().length() > 0) {
			query = session.createQuery("from Customer where lower(firstName) like :search or lower(lastName) like :search", Customer.class);
			query.setParameter("search", "%" + searchName + "%");
			List<Customer> customers = query.getResultList();
			return customers;
		}
		else {
		 	return session.createQuery("from Customer", Customer.class).getResultList();
		}
		 
	}

}

package com.ibm.movie.services;

import java.util.List;

import com.ibm.movie.entity.Customer;
import com.ibm.movie.exception.CustomerNotFoundException;


/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category CustomerService
 *
 */
public interface CustomerService {

	boolean existsById(Integer userId);

	boolean existsByMobileNumber(String mobileNo);

	boolean existsByEmail(String email);

	public Customer addCustomer(Customer customer) throws CustomerNotFoundException;

	public Customer removeCustomer(int customerid);

	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

	public Customer viewCustomer(int customerid);

	public List<Customer> viewCustomerList();
}

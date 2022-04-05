package com.ibm.movie.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Customer;
import com.ibm.movie.exception.AccessForbiddenException;
import com.ibm.movie.exception.CustomerNotFoundException;
import com.ibm.movie.services.CustomerService;

/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category Customer
 */
@CrossOrigin
@RestController
//@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private LoginController loginController;
	
	/**
	 * Checks the login status if login status is true then adds customer details.
	 * 
	 * @param customer
	 * @return customer
	 * @throws CustomerNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PostMapping(value = "/addcustomer", consumes = "application/json")
	public Customer addCustomer(@RequestBody Customer customer)
			throws CustomerNotFoundException, AccessForbiddenException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Login");
		if (!loginController.getRole().equalsIgnoreCase("ADMIN"))
			throw new AccessForbiddenException("You must be Admin to access this...");

		if (customer == null) {
			System.out.println("Error");
		} else {
		}
		return customerService.addCustomer(customer);
	}
	
	/**
	 * checks whether login status is true removes the customer using id
	 * 
	 * @param customerId
	 * @return removeCustomer
	 * @throws CustomerNotFoundException
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping(value = "/delete/{customerId}", produces = "application/json")
	public Customer removeCustomer(@PathVariable int customerId)
			throws CustomerNotFoundException, AccessForbiddenException {
		if (!loginController.loginStatus()) {
			throw new AccessForbiddenException("Not Logged In/Invalid Login");
		}
		if (!loginController.getRole().equalsIgnoreCase("ADMIN")) {
			throw new AccessForbiddenException("You must be Admin to access this...");
		}

		return customerService.removeCustomer(customerId);
	}
	
	/**
	 * checks weather login status is true updates the customer details.
	 * 
	 * @param customer
	 * @return response
	 * @throws CustomerNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PutMapping(value = "/updatecustomer", produces = "application/json")
	public Customer updateCustomer(@RequestBody Customer customer)
			throws CustomerNotFoundException, AccessForbiddenException {
		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Login");

		if (customer == null)
			System.out.println("Error");

		return customerService.updateCustomer(customer);
	}

	/**
	 * checks whether the login status is true. also verifies whether the role is admin or not displays the list of customers
	 * 
	 * @return CustomerList
	 * @throws AccessForbiddenException
	 */

	@GetMapping(value = "/findallcustomer", produces = "application/json")
	public List<Customer> viewCustomerList() throws AccessForbiddenException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Login");
		if (!loginController.getRole().equalsIgnoreCase("ADMIN"))
			throw new AccessForbiddenException("You must be Admin to access this...");

		return customerService.viewCustomerList();
	}

	/**
	 * take customerId as input and checks if the customerid is in the database
	 * or not. If the customerId not found then an exception is thrown otherwise it proceeds with the login
	 * 
	 * @return CustomerList
	 * @throws AccessForbiddenException
	 */
	@GetMapping(value = "/view/{customerId}", produces = "application/json")
	public Customer viewACustomer(@PathVariable int customerId) throws CustomerNotFoundException {
		Customer customer = new Customer();
		if (customer.getCustomerId() != customerId)
			throw new CustomerNotFoundException("Customer with " + customerId + " id dosen't exist");
		else
			customer = customerService.viewCustomer(customerId);

		return customer;
	}
}

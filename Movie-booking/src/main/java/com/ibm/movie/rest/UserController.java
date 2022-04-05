package com.ibm.movie.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Customer;
import com.ibm.movie.entity.User;
import com.ibm.movie.exception.AccessForbiddenException;
import com.ibm.movie.exception.CustomerNotFoundException;
import com.ibm.movie.exception.UserCreationError;
import com.ibm.movie.repo.CustomerRepository;
import com.ibm.movie.services.UserService;

/**
 * @author Rohit Kumar Jha
 * @version 1.0
 * @category User
 */
@CrossOrigin
@RestController
//@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Adds a user object in database
	 * @param user
	 * @return user
	 * @throws AccessForbiddenException
	 * @throws CustomerNotFoundException
	 * @throws UserCreationError
	 */
	@PostMapping(value = "/adduser", consumes = "application/json")
	public User addUser(@RequestBody User user)
			throws AccessForbiddenException, CustomerNotFoundException, UserCreationError {
		if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
			Customer customer = new Customer(user.getUsername(), null, null, null, user.getPassword());
			customerRepository.saveAndFlush(customer);
			user.setCustomer(customer);
		}
		return userService.addUser(user);
	}

	/**
	 * Remove the user object from database
	 * @param user
	 * @return remove user
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping(value = "/removeuser", consumes = "application/json")
	public User removeUser(@RequestBody User user) throws AccessForbiddenException {
		return userService.removeUser(user);
	}
}
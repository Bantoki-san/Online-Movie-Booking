package com.ibm.movie.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.services.AdminService;


/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category Login
 */

@CrossOrigin
@RestController
//@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	/**
	 * @param username
	 * @param password
	 * @return Http Status
	 * @throws Exception
	 * Here we take username and password as input and create an admin
	 */

	@PostMapping(value = "/registeradmin/{username}/{password}", consumes = "application/json")
	public HttpStatus registerAdmin(@RequestBody String username, String password) throws Exception {
		adminService.registerAdmin(username, password);
		return HttpStatus.CREATED;
	}
}
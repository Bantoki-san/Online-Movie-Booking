package com.ibm.movie.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Login;
import com.ibm.movie.services.LoginService;

/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category Login
 */
@CrossOrigin
@RestController
public class LoginController {


	@Autowired
	private LoginService logServ;

	/**
	 * verifies user name and password.
	 * 
	 * @param username
	 * @param password
	 * @return HTTP status
	 */
	@PostMapping(value = "/login/{username}/{password}", produces = "application/json")
	public Login loginUser(@PathVariable String username, @PathVariable String password) {
		Login login=new Login();
		try {
			login=logServ.loginWithData(username, password);
		} catch (Exception e) {
			return login;
		}
		return login;
	}

	/**
	 * logs out the present user.
	 * 
	 * @return HTTP status
	 * @throws Exception
	 */
	@PostMapping(value = "/Logout", produces = "application/json")
	public HttpStatus logOut() throws Exception {
		if (this.loginStatus()) {
			logServ.logoutPresentUser();
			return HttpStatus.ACCEPTED;
		} else {
			throw new Exception("User Not yet Logged In");
		}
	}

	/**
	 * Informs whether the user is logged in.
	 * 
	 * @return LoginStatus
	 */
	public boolean loginStatus() {
		return logServ.loginStatus();
	}

	/**
	 * Obtains the role of the user.
	 * 
	 * @return Role
	 */
	public String getRole() {
		return logServ.getRole();
	}

}
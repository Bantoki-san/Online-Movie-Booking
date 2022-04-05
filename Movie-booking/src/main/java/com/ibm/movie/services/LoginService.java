package com.ibm.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.movie.entity.Login;
import com.ibm.movie.entity.User;
import com.ibm.movie.repo.QueryClass;


/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category Login
 */
@Service
public class LoginService {
	@Autowired
	private QueryClass qcp;

	private Login logData = new Login();

	/**
	 * login with the username and password
	 * 
	 * @param username
	 * @param password
	 * @return logindata
	 */
	public Login loginWithData(String username, String password) throws Exception {
		User user = qcp.findByUserName(username);
		if (!user.getPassword().equals(password))
			throw new Exception("Login Data Invalid");
		logData.setLoginStatus(true);
		logData.setUser(user);
		return logData;
	}

	/**
	 * logout the current user
	 * @return logindata
	 */
	public Login logoutPresentUser() {
		if (logData.isLoginStatus()) {
			logData.setLoginStatus(false);
		}
		return logData;
	}
	
	//To check the login status
	public boolean loginStatus() {
		return logData.isLoginStatus();
	}

	//To get the role of the user
	public String getRole() {
		return logData.getRole();
	}

}

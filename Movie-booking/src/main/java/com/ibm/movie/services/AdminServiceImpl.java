package com.ibm.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.movie.entity.User;
import com.ibm.movie.exception.UserCreationError;
import com.ibm.movie.repo.AdminRepository;
import com.ibm.movie.validator.InputValidator;

/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category AdminService
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private InputValidator validate;

	/**
	 * Here we register as admin
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Override
	public void registerAdmin(String username, String password) throws Exception {
		if (!validate.usernameValidator(username))
			throw new UserCreationError("Check Username !!!!");
		if (!validate.passwordValidator(password))
			throw new UserCreationError("Cannot register this admin with this password");
		User u = new User(username, password, "ADMIN", null);
		adminRepo.saveAndFlush(u);
	}

}

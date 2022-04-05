package com.ibm.movie.services;

import com.ibm.movie.entity.User;
import com.ibm.movie.exception.UserCreationError;

/**
 * @author Rohit Kumar Jha
 * @version 1.0
 * @category UserService
 */
public interface UserService {

	public User addUser(User user) throws UserCreationError;

	public User removeUser(User user);
}

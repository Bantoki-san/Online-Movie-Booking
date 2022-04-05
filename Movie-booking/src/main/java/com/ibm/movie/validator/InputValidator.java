package com.ibm.movie.validator;

/**
 * It is the InputValidator interface
 * 
 * @author Mouli Roy
 * @version 1.0
 * @catagory InputValidator
 *
 */
public interface InputValidator {

	public boolean nameValidator(String name);

	public boolean contactValidator(String contact);

	public boolean emailValidator(String email);

	public boolean passwordValidator(String password);

	public boolean usernameValidator(String username);

}

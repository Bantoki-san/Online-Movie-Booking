package com.ibm.movie.services;

import org.springframework.stereotype.Service;


/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category AdminService
 */
@Service
public interface AdminService {

	public void registerAdmin(String username, String password) throws Exception;
}
package com.ibm.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.movie.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean existsByMobileNumber(String mobileNumber);

	boolean existsByEmail(String email);

}

package com.ibm.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.movie.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

}

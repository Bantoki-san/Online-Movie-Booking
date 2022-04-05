package com.ibm.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.movie.entity.Screen;



public interface ScreenRepository extends JpaRepository<Screen, Integer> {

}

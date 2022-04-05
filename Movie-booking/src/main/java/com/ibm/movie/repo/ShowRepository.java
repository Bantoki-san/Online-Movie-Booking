package com.ibm.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.movie.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Integer> {
	
}

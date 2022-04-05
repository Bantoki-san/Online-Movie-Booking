package com.ibm.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.movie.entity.Seat;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

}

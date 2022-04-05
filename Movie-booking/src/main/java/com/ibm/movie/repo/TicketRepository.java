package com.ibm.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.movie.entity.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}

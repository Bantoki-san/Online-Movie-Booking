package com.ibm.movie.services;

import java.util.List;

import com.ibm.movie.entity.Ticket;
import com.ibm.movie.exception.TicketNotFoundException;

/**
 * @author Sandeep Sharma
 * @version 1.0
 * @category Ticket
 *
 */
public interface TicketService {
	
	public Ticket addTicket(Ticket ticket, Integer bookingId) throws TicketNotFoundException;

	public Ticket findTicket(int ticketId);

	List<Ticket> viewTicketList() throws TicketNotFoundException;

}

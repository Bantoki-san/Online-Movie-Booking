package com.ibm.movie.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Ticket;
import com.ibm.movie.exception.AccessForbiddenException;
import com.ibm.movie.exception.TicketNotFoundException;
import com.ibm.movie.services.TicketService;

/**
 * @author Sayan Sarkar
 * @version 1.0
 * @category Ticket
 */
@CrossOrigin
@RestController
//@RequestMapping("/tickets")
public class TicketController {


	@Autowired
	private TicketService ticketService;

	/**
	 * Stores a ticket object in database
	 * 
	 * @param ticket
	 * @return added ticket
	 * @throws TicketNotFoundException
	 */
	@PostMapping(value = "/addticket", consumes = "application/json")
	public Ticket addATicket(@RequestBody Ticket ticket,@RequestParam(required = false) Integer bookingId) throws AccessForbiddenException, TicketNotFoundException {
		
		ticket = ticketService.addTicket(ticket,bookingId);
		System.out.println(ticket.getSeats()); 
		return ticket;
	}

	/**
	 * Return list of ticket from database
	 * 
	 * @return ticketList
	 * @throws AccessForbiddenException
	 * @throws TicketNotFoundException
	 */
	@GetMapping(value = "/findallticket", produces = "application/json")
	public List<Ticket> viewTicketList() throws AccessForbiddenException, TicketNotFoundException {
		return ticketService.viewTicketList();
	}

	/**
	 * Return list of ticket by ticketId
	 * @param ticketId
	 * @return ticket by ticketId
	 * @throws TicketNotFoundException
	 * @throws AccessForbiddenException
	 */
	@GetMapping(value = "/findticket/{ticketId}", produces = "application/json")
	public Ticket findATicket(@PathVariable int ticketId) throws TicketNotFoundException, AccessForbiddenException {	
		Ticket ticket = null;
		try {
			ticket = ticketService.findTicket(ticketId);
		} catch (Exception e) {
			throw new TicketNotFoundException("Invalid Ticket ID");
		}
		return ticket;
	}

}

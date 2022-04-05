package com.ibm.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.movie.entity.Booking;
import com.ibm.movie.entity.Seat;
import com.ibm.movie.entity.Ticket;
import com.ibm.movie.exception.TicketNotFoundException;
import com.ibm.movie.repo.BookingRepository;
import com.ibm.movie.repo.SeatRepository;
import com.ibm.movie.repo.TicketRepository;

/**
 * @author Sandeep Sharma
 * @version 1.0
 * @category Ticket
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;

	//Parameterized Constructor
	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private BookingRepository bookingRepository;

	/**
	 * Add ticket object
	 * @param ticket
	 * @param transactionId
	 * @return ticket
	 * @throws TicketNotFoundException
	 */
	@Override
	public Ticket addTicket(Ticket ticket, Integer transactionId) throws TicketNotFoundException {
		Booking booking = new Booking();
		if (transactionId != null) {
			booking = bookingRepository.findById(transactionId).get();
			booking.setTransactionStatus("Completed");
			ticket.setBooking(booking);
		}
		ticketRepository.saveAndFlush(ticket);

		for (Seat s : ticket.getSeats()) {
			s.setTickett(ticket);
			seatRepository.saveAndFlush(s);
		}

		return ticket;
	}

	/**
	 * List of ticket
	 * @return ticket
	 * @throws TicketNotFoundException
	 */
	@Override
	public List<Ticket> viewTicketList() throws TicketNotFoundException {
		List<Ticket> ti = ticketRepository.findAll();
		if (ti.size() == 0)
			throw new TicketNotFoundException("No tickets are avaliable");
		return ti;
	}

	/**
	 * Find ticket by id
	 * @param ticketId
	 * @return ticket
	 */
	@Override
	public Ticket findTicket(int ticketId) {
		return ticketRepository.findById(ticketId).get();
	}

}

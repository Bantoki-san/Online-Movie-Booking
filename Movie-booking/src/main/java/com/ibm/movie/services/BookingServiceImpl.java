package com.ibm.movie.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.movie.entity.Booking;
import com.ibm.movie.entity.Customer;
import com.ibm.movie.entity.Seat;
import com.ibm.movie.entity.Show;
import com.ibm.movie.entity.Ticket;
import com.ibm.movie.exception.BookingNotFoundException;
import com.ibm.movie.repo.BookingRepository;
import com.ibm.movie.repo.CustomerRepository;
import com.ibm.movie.repo.QueryClass;
import com.ibm.movie.repo.ShowRepository;
import com.ibm.movie.repo.TicketRepository;

/**
 * @author Satadru Banerjee
 * @version 1.0
 * @category BookingService
 *
 */
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private CustomerRepository custoRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private QueryClass query;

	/**
	 * Add a booking 
	 * @param booking
	 * @param customerId
	 * @param showId
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public Booking addBooking(Booking booking, Integer customerId, Integer showId) throws BookingNotFoundException {
		Customer customer = new Customer();
		Show show = new Show();
		if (showId != null) {
			customer = custoRepository.getById(customerId);
			show = showRepository.findById(showId).get();
			show.setBooking(booking);
			booking.setCustomer(customer);
			booking.setShow(show);
		}
		bookingRepository.saveAndFlush(booking);
		showRepository.saveAndFlush(show);
		return bookingRepository.findById(booking.getTransactionId()).get();
	}

	/**
	 * Update booking 
	 * @param booking
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public Booking updateBooking(Booking booking) throws BookingNotFoundException {
		bookingRepository.saveAndFlush(booking);
		return bookingRepository.getById(booking.getTransactionId());
	}

	/**
	 * Cancel a booking 
	 * @param bookingid
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public Booking cancelBooking(int bookingid) throws BookingNotFoundException {
		Booking b = bookingRepository.getById(bookingid);
		bookingRepository.delete(b);
		return b;
	}

	/**
	 * Show all booking 
	 * @param movieid
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public List<Booking> showAllBookings(int movieid) throws BookingNotFoundException {
		List<Booking> bk = query.getAllByMovieId(movieid);

		if (bk.size() == 0)
			throw new BookingNotFoundException("No bookings found");

		return bk;
	}

	/**
	 * Show all booking list 
	 * @param booking
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public List<Booking> showAllBookings(LocalDate bookingdate) throws BookingNotFoundException {
		List<Booking> bkList = new ArrayList<>();
		for (Booking booking : bookingRepository.findAll()) {
			if (booking.getBookingDate() != null && booking.getBookingDate().isEqual(bookingdate)) {
				bkList.add(booking);
			}
		}
		if (bkList.size() == 0)
			throw new BookingNotFoundException("No bookings found");
		else {
			return bkList;
		}
	}

	/**
	 * Calculate the total cost 
	 * @param bookingid
	 * @return booking
	 */
	@Override
	public double calculateTotalCost(int bookingid) {
		List<Ticket> tickets = ticketRepository.findAll();
		Set<Seat> Seats = new HashSet<>();
		for (Ticket ticket : tickets) {
			if (bookingid == ticket.getBooking().getTransactionId()) {
				Seats.addAll(ticket.getSeats());
			}
		}
		double amount = 0;
		for (Seat seat : Seats) {
			amount = amount + seat.getPrice();
		}
		Booking booking = bookingRepository.getById(bookingid);
		booking.setTotalCost(amount);
		bookingRepository.saveAndFlush(booking);
		return amount;
	}

	/**
	 * View full booking list
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public List<Booking> viewBookingList() throws BookingNotFoundException {
		List<Booking> bk = bookingRepository.findAll();

		if (bk.size() == 0)
			throw new BookingNotFoundException("No bookings found");

		return bk;
	}

	/**
	 * view booking
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public Booking viewBooking(int bookingid) throws BookingNotFoundException {
		return bookingRepository.findById(bookingid).get();
	}

}

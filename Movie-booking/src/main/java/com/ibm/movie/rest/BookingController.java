package com.ibm.movie.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Booking;
import com.ibm.movie.exception.AccessForbiddenException;
import com.ibm.movie.exception.BookingNotFoundException;
import com.ibm.movie.services.BookingService;

/**
 * @author Satadru Banerjee
 * @version 1.0
 * @catagory Booking
 */
@CrossOrigin
@RestController
//@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private LoginController loginController;
	
	/**
	 * Checks the login status if login status is true then adds ticket booking
	 * 
	 * @param booking
	 * @param customerId
	 * @return addBooking
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@PostMapping(value = "/insert", consumes = "application/json")
	public Booking addTicketBooking(@RequestBody Booking booking, @RequestParam(required = false) Integer customerId,
			@RequestParam(required = false) Integer showId) throws AccessForbiddenException, BookingNotFoundException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Login");

		return bookingService.addBooking(booking, customerId, showId);
	}
	
	/**
	 * Checks the login status if login status is true then calls viewbookingList
	 * 
	 * @return viewBookingList
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping(value = "/findallbooking", produces = "application/json")
	public List<Booking> viewAllBookings() throws AccessForbiddenException, BookingNotFoundException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Loggin");

		return bookingService.viewBookingList();
	}
	
	/**
	 * Checks the login status if login status is true then update the ticket booking
	 * 
	 * @param booking
	 * @return updated booking details
	 * @throws BookingNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PutMapping(value = "/updatebooking", produces = "application/json")
	public Booking updateTicketBooking(@RequestBody Booking booking) throws BookingNotFoundException, AccessForbiddenException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Loggin");

		return bookingService.updateBooking(booking);
	}
	
	/**
	 * Checks the login status if login status is true then deletes ticket booking
	 * 
	 * @param bookingId
	 * @return cancel booking
	 * @throws BookingNotFoundException
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping(value = "ticketbooking/{bookingId}", produces = "application/json")
	public Booking deleteTicketBookingById(@PathVariable int bookingId)throws BookingNotFoundException, AccessForbiddenException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Loggin");

		return bookingService.cancelBooking(bookingId);
	}
	
	/**
	 * @param bookingId
	 * @return viewBooking
	 * @throws BookingNotFoundException
	 */
	@GetMapping(value = "/viewbooking/{bookingId}", produces = "application/json")
	public Booking viewBooking(@PathVariable int bookingId) throws BookingNotFoundException {
		return bookingService.viewBooking(bookingId);
	}
	
	/**
	 * @param movieId
	 * @return bookingList by movie id
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping(value = "/byMovie/{movieId}", produces = "application/json")
	public List<Booking> viewMovieByMovieId(@PathVariable int movieId) throws AccessForbiddenException, BookingNotFoundException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Loggin");

		return bookingService.showAllBookings(movieId);
	}
	
	/**
	 * @param date
	 * @return bookingList by date
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping(value = "/byDate/{date}", produces = "application/json")
	public List<Booking> viewMovieByLocalDate(@RequestParam("bookingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
			throws AccessForbiddenException, BookingNotFoundException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Loggin");

		return bookingService.showAllBookings(date);
	}

	/**
	 * @param bookingId
	 * @return total cost of booking
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping(value = "/cost/{bookingId}", consumes = "application/json")
	public double TotalBookingcost(@PathVariable int bookingId) throws AccessForbiddenException, BookingNotFoundException {

		if (!loginController.loginStatus())
			throw new AccessForbiddenException("Not Logged In/Invalid Loggin");

		return bookingService.calculateTotalCost(bookingId);
	}
}

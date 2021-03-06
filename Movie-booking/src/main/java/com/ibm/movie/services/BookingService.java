package com.ibm.movie.services;

import java.time.LocalDate;
import java.util.List;

import com.ibm.movie.entity.Booking;
import com.ibm.movie.exception.BookingNotFoundException;


/**
 * @author Satadru Banerjee
 * @version 1.0
 * @category BookingService
 *
 */
public interface BookingService {
	public Booking addBooking(Booking booking, Integer customerId, Integer showId) throws BookingNotFoundException;

	public List<Booking> viewBookingList() throws BookingNotFoundException;

	public Booking updateBooking(Booking booking) throws BookingNotFoundException;

	public Booking cancelBooking(int bookingid) throws BookingNotFoundException;

	public List<Booking> showAllBookings(int movieid) throws BookingNotFoundException;

	public Booking viewBooking(int bookingid) throws BookingNotFoundException;

	public List<Booking> showAllBookings(LocalDate bookingdate) throws BookingNotFoundException;

	public double calculateTotalCost(int bookingid);

}

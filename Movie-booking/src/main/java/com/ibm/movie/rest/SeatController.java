package com.ibm.movie.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Seat;
import com.ibm.movie.exception.AccessForbiddenException;
import com.ibm.movie.exception.SeatNotFoundException;
import com.ibm.movie.services.SeatService;

/**
 * @author Satadru Banerjee
 * @version 1.0
 * @category Movie
 */
@CrossOrigin
@RestController
//@RequestMapping("/seats")
public class SeatController {

	@Autowired
	private SeatService seatService;

	/**
	 * add seat object into the database
	 * 
	 * @param seat
	 * @return seatDetails
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PostMapping(value = "/addseat", consumes = "application/json")
	public Seat addASeat(@RequestBody Seat seat) throws AccessForbiddenException, SeatNotFoundException {
		
		seat = seatService.addSeat(seat);
		return seat;
	}

	/**
	 * Returns the List of seat from the Database
	 * 
	 * @return listOfSeats
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@GetMapping(value = "/findallseat", produces = "application/json")
	public List<Seat> viewSeatList() throws AccessForbiddenException, SeatNotFoundException {
		
		return seatService.viewSeatList();
	}

	/**
	 * Update the seat object
	 * 
	 * @param seat
	 * @return seat
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PutMapping(value = "/updateseat", produces = "application/json")
	public Seat updateSeat(@RequestBody Seat seat)throws AccessForbiddenException, SeatNotFoundException {
	
		if (seat == null) {
			System.out.println("Error");
		} else {
			seat = seatService.updateSeat(seat);
		}
		return seat;
	}

	/**
	 * Book the seat selected by the customer
	 * 
	 * @param seat
	 * @return seat
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PutMapping(value = "/bookseat", produces = "application/json")
	public Seat BookASeat(@RequestBody Seat seat) throws AccessForbiddenException, SeatNotFoundException {
		
		seat = seatService.bookSeat(seat);
		return seat;
	}

	/**
	 * cancel the seat booking
	 * 
	 * @param seat
	 * @return seat
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PutMapping(value = "/cancel", produces = "application/json")
	public Seat CancelASeat(@RequestBody Seat seat) throws AccessForbiddenException, SeatNotFoundException {
		seat = seatService.cancelSeatBooking(seat);
		return seat;
	}

	/**
	 * block the selected seat
	 * 
	 * @param seat
	 * @return blocked seat
	 * @throws AccessForbiddenException
	 * @throws SeatNotFoundException
	 */
	@PutMapping(value = "/block", produces = "application/json")
	public Seat BloclASeat(@RequestBody Seat seat) throws AccessForbiddenException, SeatNotFoundException {
		seat = seatService.blockSeat(seat);
		return seat;

	}
}

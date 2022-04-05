package com.ibm.movie.services;

import java.util.List;

import com.ibm.movie.entity.Seat;
import com.ibm.movie.exception.SeatNotFoundException;


/**
 * @author Sayan Sarkar
 * @version 1.0
 * @category Seat
 *
 */
public interface SeatService {

	public Seat addSeat(Seat seat) throws SeatNotFoundException;

	public List<Seat> viewSeatList() throws SeatNotFoundException;

	public Seat updateSeat(Seat seat);

	public Seat bookSeat(Seat seat);

	public Seat cancelSeatBooking(Seat seat);

	public Seat blockSeat(Seat seat);
}

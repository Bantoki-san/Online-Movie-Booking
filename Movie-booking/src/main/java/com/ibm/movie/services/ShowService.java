package com.ibm.movie.services;

import java.time.LocalDate;
import java.util.List;

import com.ibm.movie.entity.Show;

/**
 * @author Sayan Sarkar
 * @version 1.0
 * @category Show
 *
 */
public interface ShowService {

	public Show addShow(Show show, Integer screenId);

	public Show updateShow(Show show, Integer screenId);

	public Show removeShow(int showid);

	public Show viewShow(int showid);

	public List<Show> viewAllShows();

	public List<Show> viewShowList(LocalDate date);

}

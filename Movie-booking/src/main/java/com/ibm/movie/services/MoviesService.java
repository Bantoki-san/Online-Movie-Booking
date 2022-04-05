package com.ibm.movie.services;

import java.time.LocalDate;
import java.util.List;

import com.ibm.movie.entity.Movie;
import com.ibm.movie.exception.MovieNotFoundException;


/**
 * @author Satadru Banerjee
 * @version 1.0
 * @category MovieService
 *
 */
public interface MoviesService {

	public Movie addMovie(Movie movie) throws MovieNotFoundException;

	public Movie removeMovie(int movieid) throws MovieNotFoundException;
	
	public Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	public Movie addMovieToShow(Movie movie, Integer showId) throws MovieNotFoundException;

	public Movie viewMovie(int movieid) throws MovieNotFoundException;

	public List<Movie> viewMovieList() throws MovieNotFoundException;

	public List<Movie> viewMovieList(LocalDate date);
}

package com.ibm.movie.rest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Movie;
import com.ibm.movie.exception.MovieNotFoundException;
import com.ibm.movie.services.MoviesService;

/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category Movie
 */
//@CrossOrigin
@RestController
//@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private MoviesService moviesService;

	/**
	 * Stores a Movie object in the Database.
	 * 
	 * @param movie
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws IOException 
	 * @throws AccessForbiddenException
	 */
	@PostMapping(value = "/addmovie", consumes = "application/json")
	public Movie addMovie(@RequestBody Movie movie) throws MovieNotFoundException, IOException {
		movie = moviesService.addMovie(movie);
		System.out.println("Movie Added");
		return movie;
	}

	/**
	 * Update the movie object
	 * 
	 * @param movie
	 * @return Movie
	 * @throws MovieNotFoundException
	 */
	@PutMapping(value = "/updatemovie", produces = "application/json")
	public Movie updateMovie(@RequestBody Movie movie) throws MovieNotFoundException {
		if (movie == null) {
			System.out.println("Error");
		} else {
			movie = moviesService.updateMovie(movie);
		}
		return movie;
	}

	/**
	 * Add movie to show
	 * 
	 * @param movie
	 * @param showId
	 * @return Movie
	 * @throws MovieNotFoundException
	 */
	@PutMapping(value = "/maptoshow", produces = "application/json")
	public Movie addToShow(@RequestBody Movie movie, @RequestParam(required = false) Integer showId)
			throws MovieNotFoundException {
		if (movie == null) {
			System.out.println("Error");
		} else {
			movie = moviesService.addMovieToShow(movie, showId);
		}
		return movie;
	}

	/**
	 * Return's the List of Movies from the Database
	 * 
	 * @return List<Movie>
	 * @throws MovieNotFoundException
	 */
	@GetMapping(value = "/findallmovie", produces = "application/json")
	public List<Movie> viewMovieList() throws MovieNotFoundException {
		return moviesService.viewMovieList();
	}

	/**
	 * Returns the record from the database from movieId
	 * 
	 * @param movieId
	 * @return Movie
	 * @throws MovieNotFoundException
	 */
	@GetMapping(value = "/viewMovie/{movieId}", produces = "application/json")
	public Movie viewMovie(@PathVariable int movieId) throws MovieNotFoundException {
		Movie movie = moviesService.viewMovie(movieId);
		if (movie.getMovieId() != movieId)
			throw new MovieNotFoundException("Movie with " + movieId + " doesn't exist");
		else
			movie = moviesService.viewMovie(movieId);
		return movie;
	}

	/**
	 * Removes movie object
	 * 
	 * @param movieId
	 * @return Movie
	 * @throws MovieNotFoundException
	 */
	@DeleteMapping(value = "/delete/{movieId}", produces = "application/json")
	public Movie removeMovie(@PathVariable int movieId) throws MovieNotFoundException {

		Movie movie = moviesService.viewMovie(movieId);
		if (movie.getMovieId() != movieId)
			throw new MovieNotFoundException("Movie with \" + movieId + \" doesn't exist");
		else
			movie = moviesService.removeMovie(movieId);
		return movie;
	}

	/**
	 * Returns the record from the database from localDate
	 * 
	 * @param date
	 * @return MovieList
	 * @throws MovieNotFoundException
	 */
	@GetMapping(value = "/MbyDate/{date}", produces = "application/json")
	public List<Movie> viewMovieByLocalDate(
			@RequestParam("movieDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return moviesService.viewMovieList(date);
	}
}

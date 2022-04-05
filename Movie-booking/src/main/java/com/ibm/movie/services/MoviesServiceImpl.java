package com.ibm.movie.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.movie.entity.Movie;
import com.ibm.movie.entity.Show;
import com.ibm.movie.exception.MovieNotFoundException;
import com.ibm.movie.repo.MoviesRepository;
import com.ibm.movie.repo.ShowRepository;

/**
 * @author Satadru Banerjee
 * @version 1.0
 * @category MovieService
 *
 */
@Service
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private MoviesRepository moviesrepository;
	@Autowired
	private ShowRepository showrepository;

	/**
	 *Add Movie object in database
	 *@param movie
	 *@return movie
	 *@throws MovieNotFoundException
	 */
	@Override
	public Movie addMovie(Movie movie) throws MovieNotFoundException {
		if (movie != null) {
			if (moviesrepository.existsById(movie.getMovieId())) {
				throw new MovieNotFoundException("Movie with this id already exists");
			} else {
				moviesrepository.saveAndFlush(movie);
			}
		}
		return movie;
	}

	/**
	 *Remove Movie object
	 *
	 *@param movieid
	 *@return movie
	 */
	@Override
	public Movie removeMovie(int movieid) {
		Movie m = moviesrepository.findById(movieid).get();
		List<Show> shows = showrepository.findAll();
		for (Show show : shows) {
			if (show.getMovie()!=null && show.getMovie().getMovieId() == movieid) {
				show.setMovie(null);
			}
		}
		moviesrepository.delete(m);
		return m;
	}
	
	/**
	 *Update Movie object
	 *
	 *@param movie
	 *@return movie
	 */
	@Override
	public Movie updateMovie(Movie movie) {
		moviesrepository.saveAndFlush(movie);
		return moviesrepository.getById(movie.getMovieId());
	}
	
	/**
	 *Add movie to show
	 *
	 *@param movie
	 *@param showId
	 *@return movie
	 */
	@Override
	public Movie addMovieToShow(Movie movie,Integer showId) {
		Show show=new Show();
		if (showId != null) {
			show = showrepository.getById(showId);
			movie.setShow(show);
		}
		moviesrepository.saveAndFlush(movie);
		return moviesrepository.getById(movie.getMovieId());
	}

	/**
	 *View movie by movie ID
	 *
	 *@param movieid
	 *@return movie
	 */
	@Override
	public Movie viewMovie(int movieid) {
		return moviesrepository.findById(movieid).get();
	}

	/**
	 *View movie List
	 *
	 *@return movie
	 *@throws MovieNotFoundException
	 */
	@Override
	public List<Movie> viewMovieList() throws MovieNotFoundException {
		List<Movie> movie = moviesrepository.findAll();
		if (movie.size() == 0) 
			throw new MovieNotFoundException("Movies dosen't exist");
		return movie;
	}

	/**
	 *View movie List by localDate
	 *
	 *@param date
	 *@return movielist
	 */
	@Override
	public List<Movie> viewMovieList(LocalDate date) {
		List<Movie> mvList = new ArrayList<>();
		for (Movie movie : moviesrepository.findAll()) {
			if (movie.getMovieDate() != null && movie.getMovieDate().isEqual(date)) {
				mvList.add(movie);
			}
		}
		return mvList;
	}


}

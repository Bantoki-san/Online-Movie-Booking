package com.ibm.movie.entity;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author Satadru Banerjee
 * @version 1.0
 * @category MovieEntity
 */
@Entity
@Table(name = "movie")
@DynamicUpdate
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int movieId;
	private String movieName;
	private String movieGenre;
	private String movieHours;
	private String movieLanguage;
	private String movieDescription;
	private String movieRating;
	private LocalDate movieDate;
	@OneToOne
	private Show show;

	public Movie() {

	}

	public Movie(String movieName, String movieGenre, String movieHours, String movieLanguage, String movieDescription,
			String movieRating, LocalDate movieDate, Show show) {
		super();
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieHours = movieHours;
		this.movieLanguage = movieLanguage;
		this.movieDescription = movieDescription;
		this.movieRating = movieRating;
		this.movieDate = movieDate;
		this.show = show;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getMovieHours() {
		return movieHours;
	}

	public void setMovieHours(String movieHours) {
		this.movieHours = movieHours;
	}

	public String getMovieLanguage() {
		return movieLanguage;
	}

	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public LocalDate getMovieDate() {
		return movieDate;
	}

	public void setMovieDate(LocalDate movieDate) {
		this.movieDate = movieDate;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

}

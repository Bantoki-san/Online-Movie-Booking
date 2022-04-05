package com.ibm.movie.exception;

public class MovieNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public MovieNotFoundException() {

	}

	public MovieNotFoundException(String message) {
		super(message);
	}
}

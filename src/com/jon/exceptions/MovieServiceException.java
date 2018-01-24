package com.jon.exceptions;

public class MovieServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	public static String STATUS_OK = "status_ok";
	public static String REQUEST_NOT_READY_EXCEPTION = "Service is not ready currently.";
	public static String MOVIE_NOT_FOUND_EXCEPTION = "No information found for this entry.";
	public static String REQUEST_FORMAT_INCORRECT_EXCEPTION = "Request format is not correct.";
	public static String NOT_VALID_MOVIE_NAME = "Movie name is not correct";

	public MovieServiceException() {
	}

	public MovieServiceException(String message) {
		super(message);
	}

	public MovieServiceException(Throwable cause) {
		super(cause);
	}

	public MovieServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MovieServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

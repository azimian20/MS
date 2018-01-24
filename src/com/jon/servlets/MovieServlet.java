package com.jon.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jon.exceptions.MovieServiceException;
import com.jon.model.Movie;
import com.jon.service.MovieServiceReceiver;

@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieServiceReceiver movieServiceReceiver = new MovieServiceReceiver();

	public MovieServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Movie> movies = new ArrayList<Movie>();
		String errorMessage = MovieServiceException.STATUS_OK;
		try {
			String movieName = movieServiceReceiver.trimMovieName(request.getParameter("movieName"));
			movies = movieServiceReceiver.getMovieInfo(movieName);
			// --in case of exception, movies list will be returned empty.
		} catch (MovieServiceException e) {
			errorMessage = e.getMessage();
		}
		request.setAttribute("movies", movies);
		request.setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher("/movieSearch.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

package com.jon.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jon.exceptions.MovieServiceException;
import com.jon.service.MovieServiceReceiver;

@WebServlet("/MovieServletClientHandled")
public class MovieServletClientHandled extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieServiceReceiver movieServiceReceiver = new MovieServiceReceiver();

	public MovieServletClientHandled() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorMessage = MovieServiceException.STATUS_OK;
		try {
			String movieName = movieServiceReceiver.trimMovieName(request.getParameter("movieName"));
			String movieInfo = movieServiceReceiver.getMovieInfoClientHandled(movieName);
			response.getWriter().write(movieInfo);
			// --in case of exception, movies list will be returned empty.
		} catch (MovieServiceException e) {
			errorMessage = e.getMessage();
			response.setHeader("errorMessage", errorMessage);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT); //--Using 203 for business error
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

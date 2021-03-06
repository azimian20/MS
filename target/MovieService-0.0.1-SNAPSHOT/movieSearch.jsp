
<%@page import="com.jon.exceptions.MovieServiceException"%>
<%@page import="com.jon.model.MovieAttribute"%>
<%@ page import="com.jon.model.Movie"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Movie Search</title>
<style>
body {
	font-family: Arial;
	font-size: 13px;
}

label {
	padding-left: 20px;
}

.input {
	padding: 5px;
	margin: 7px;
	width: 230px;
	border-radius: 4px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	font-size: 15px;
}

.button {
	padding: 6px;
	width: 100px;
	margin: 7px;
	background-color: rgb(290, 250, 205);
	border: 0;
	font-size: 13px;
	border-radius: 4px;
	cursor: pointer;
}

.movieLine {
	box-sizing: border-box;
	background-color: rgb(240, 200, 110);
	border: 0;
	font-family: Arial;
	font-size: 14px;
	border-radius: 4px;
	margin: 3px;
	margin-bottom: 4px;
	padding: 3px;
}

.itemTitle {
	width: 100px;
	display: inline-block;
	float: left;
	clear: left;
	height: 20px;
	margin: 5px;
	text-align: right
}

.itemContent {
	background-color: rgb(245, 210, 120);
	border: 0;
	border-radius: 4px;
	display: inline-block;
	clear: right;
	float: left;
	max-width: 800px;
	overflow: hidden;
	text-align: left;
	padding: 5px;
	margin: 3px;
}
</style>
</head>
<body>
	<form action="MovieServlet" method="post" id="movieServletForm">
		<div class="movieLine">
			<label for="movieName">Movie name:</label> <input type="text"
				name="movieName" class="input" required/> <input type="submit"
				value="search" class="button" />
		</div>
	</form>

	<article>
		<%
			if ((request.getAttribute("errorMessage") != null)
					&& (!(request.getAttribute("errorMessage").equals(MovieServiceException.STATUS_OK)))) {
				String errorMessage = request.getAttribute("errorMessage").toString();
		%>
		<div class="movieLine">
			<strong><%=errorMessage%></strong>
		</div>
		<%
			} //--Exceptions and validation errors 
		%>
		<%
			if (request.getAttribute("movies") != null) {
				List<Movie> movies = (List<Movie>) request.getAttribute("movies");
				for (Movie movie : movies) {
		%>
		<div class="movieLine">
			<%
				for (MovieAttribute movieAttribute : movie.getAttributes()) {
			%>
			<div class="itemTitle">
				<strong><%=movieAttribute.getItemTitle()%>:</strong>
			</div>
			<div class="itemContent"><%=movieAttribute.getItemContent()%></div>
			<%
				}
			%>
			<div style="clear: both"></div>
		</div>
		<%
			}
			}
		%>


	</article>
</body>
</html>

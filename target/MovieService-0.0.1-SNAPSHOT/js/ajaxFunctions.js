function getXMLHttpRequest() {
	var xmlHttpReq;
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				alert("Exception in getXMLHttpRequest()!");
			}
		}
	}
	return xmlHttpReq;
}

function searchMovies() {
	var xmlHttpRequest = getXMLHttpRequest();
	xmlHttpRequest.onreadystatechange = getReadyStateHandlerPullRequest(xmlHttpRequest);
	var movieName = document.getElementsByName("movieName")[0].value;
	document.getElementsByName("movieName")[0].value = "";
	// --URL Parameters:
	var urlParameters = "?";
	urlParameters = urlParameters + "movieName=" + movieName;
	xmlHttpRequest.open("POST", "MovieServletClientHandled" + urlParameters,
			true); // --
	xmlHttpRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttpRequest.send(null);
}

function getReadyStateHandlerPullRequest(xmlHttpRequest) {
	return function() { //--handle error messages and exceptions
		if (xmlHttpRequest.readyState == 4) {
			if (xmlHttpRequest.status == 200) {
				var jsonMovieContainer = JSON.parse(xmlHttpRequest.responseText); //--if error message comes.
				var movieResultList = new Array();
				movieResultList = jsonMovieContainer.results;
				for (var i = 0; i < movieResultList.length; i++) {
					var movieLine = createMovieLine();
					createMovieAttribLine(movieLine, "Movie Name", movieResultList[i].title);
					createMovieAttribLine(movieLine, "Popularity", movieResultList[i].popularity);
					createMovieAttribLine(movieLine, "Overview", movieResultList[i].overview);
					finishMovieLine(movieLine);
				}
				
				// --fields:
				/*
				 * "vote_count": 7641, "id": 597, "video": false,
				 * "vote_average": 7.5, "title": "Titanic", "popularity":
				 * 295.232887, "poster_path":
				 * "/kHXEpyfl6zqn8a6YuozZUujufXf.jpg", "original_language":
				 * "en", "original_title": "Titanic", "genre_ids": [ 18, 10749,
				 * 53 ], "backdrop_path": "/fVcZErSWa7gyENuj8IWp8eAfCnL.jpg",
				 * "adult": false, "overview":
				 */
			} else {
				alert("Http error " + xmlHttpRequest.status + ":"
						+ xmlHttpRequest.statusText);
			}
		}
	};
}

function createMovieLine() {
	var movieInfoConainer = document.getElementById("movieInfoConainer");
	movieInfoConainer.innerHTML = "";
	var movieLineDiv = document.createElement("div");
	movieLineDiv.setAttribute("class", "movieLine");
	movieInfoConainer.appendChild(movieLineDiv);
	return movieLineDiv;
}

function finishMovieLine(parentTag) {
	var movieLineBreaker = document.createElement("div");
	movieLineBreaker.setAttribute("style", "clear: both");
	parentTag.appendChild(movieLineBreaker);
}

function createMovieAttribLine(parentTag, attribTitle, attribDescription) {
	// --Per each movie attribute item:
	var movieAttribTitleDiv = document.createElement("div");
	movieAttribTitleDiv.setAttribute("class", "itemTitle");

	var attribTitleText = document.createElement("strong");
	attribTitleText.innerHTML = attribTitle + ":";
	movieAttribTitleDiv.appendChild(attribTitleText);

	parentTag.appendChild(movieAttribTitleDiv);

	var movieAttribDescriptionDiv = document.createElement("div");
	movieAttribDescriptionDiv.setAttribute("class", "itemContent");
	movieAttribDescriptionDiv.innerHTML = attribDescription;
	
	parentTag.appendChild(movieAttribDescriptionDiv);
	
	var movieLineBreaker = document.createElement("div");
	movieLineBreaker.setAttribute("style", "clear: both");
	parentTag.appendChild(movieLineBreaker);
}
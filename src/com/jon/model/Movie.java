package com.jon.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	public Movie() {
		this.setAttributes(new ArrayList<MovieAttribute>());
	}
	private List<MovieAttribute> attributes;
	public List<MovieAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<MovieAttribute> attributes) {
		this.attributes = attributes;
	}
}

package com.captech.walk2.rottentomatoes;

public class Celebrity {

	private String name;
	private String movieTitle;
	
	public Celebrity() {
		name = "";
		movieTitle = "";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
}

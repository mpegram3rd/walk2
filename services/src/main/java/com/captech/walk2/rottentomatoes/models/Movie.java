package com.captech.walk2.rottentomatoes.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
@XmlAccessorType( XmlAccessType.FIELD )
public class Movie {
	
	private int id;
	private String title;
	private int criticalRating;
	private List<String> reviews;
	
	public Movie() {
		reviews = new ArrayList<String>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCriticalRating() {
		return criticalRating;
	}
	public void setCriticalRating(int criticalRating) {
		this.criticalRating = criticalRating;
	}
	public List<String> getReviews() {
		return reviews;
	}
	public void setReviews(List<String> reviews) {
		this.reviews = reviews;
	}
	
}

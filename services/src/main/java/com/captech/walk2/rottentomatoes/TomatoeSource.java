package com.captech.walk2.rottentomatoes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Component
public class TomatoeSource {

	@Autowired
	private Configuration config;
	
	@Autowired
	private RottenMoviesToCelebrityTransformer celebTransformer;
	
	public Celebrity getCeleb() {

		Celebrity celeb = null;
		
		List<Celebrity> celebs = rawCelebs();
		if (celebs != null && celebs.size() > 0) {
			celeb = celebs.get((int)(Math.random() * celebs.size()));
		}
		return celeb;
	}
	
	public String createReview(String id) {
		
		String result = "I laughed, I cried it became a part of me!";
		List<String> quotes = rawReviews(id);
		if (quotes != null && quotes.size() > 0) {
			result = quotes.get((int)(Math.random() * quotes.size()));
		}
		return result;
	}
	
	// make cacheable
	protected List<String> rawReviews(String id) {
		List<String> quotes = new ArrayList<String>();
		
		ClientResponse cr = getReviewResource(id).accept("application/json")
									.get(ClientResponse.class);
		
		if (cr.getStatus() == 200) {
			try {
				JSONObject topLevel = new JSONObject(cr.getEntity(String.class));
				if (topLevel.length() > 0) {
					JSONArray jsonReviews = topLevel.getJSONArray("reviews");
					for (int index = 0; index < jsonReviews.length(); index++) {
						JSONObject jsonReview = jsonReviews.getJSONObject(index);
						quotes.add(jsonReview.getString("quote"));
					}
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return quotes;
	}
	
	// Make cacheable
	protected List<Celebrity> rawCelebs() {
		
		List<Celebrity> celebs = new ArrayList<Celebrity>();
		
		ClientResponse cr = getCelebResource().accept("application/json")
									.get(ClientResponse.class);
		
		if (cr.getStatus() == 200) {
			try {
				JSONObject topLevel = new JSONObject(cr.getEntity(String.class));
				if (topLevel.length() > 0) {
					JSONArray jsonMovies = topLevel.getJSONArray("movies");
					celebs = celebTransformer.transform(jsonMovies);
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return celebs;
		
	}

	protected WebResource getReviewResource(String id) {
		String apiKey = config.getString("rotten.key");
		String baseURL = "http://api.rottentomatoes.com/api/public/v1.0/movies/" + id + "/reviews.json?apikey=" + apiKey;
		StringBuilder builder = new StringBuilder(baseURL)
						.append("&limit=50")
						.append("&country=us");
		Client client = Client.create();
		
		
		WebResource webResource = client.resource(builder.toString());

		return webResource;
	}
	
	protected WebResource getCelebResource() {
		String apiKey = config.getString("rotten.key");
		String baseURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=" + apiKey;
		StringBuilder builder = new StringBuilder(baseURL)
						.append("&limit=50")
						.append("&country=us");
		Client client = Client.create();
		
		
		WebResource webResource = client.resource(builder.toString());

		return webResource;
	}
}

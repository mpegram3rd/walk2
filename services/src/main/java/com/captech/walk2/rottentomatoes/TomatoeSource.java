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
	
	public String createReview() {
		
		return "I laughed I cried it became a part of me";
	}
	
	// make cacheable
	protected List<String> rawReviews() {
		
		return null;
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
	
	protected WebResource getCelebResource() {
		String apiKey = config.getString("rotten.key");
		String baseURL = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/top_rentals.json?apikey=" + apiKey;
		StringBuilder builder = new StringBuilder(baseURL)
						.append("&limit=50")
						.append("&country=us");
		Client client = Client.create();
		
		
		WebResource webResource = client.resource(builder.toString());

		return webResource;
	}
}

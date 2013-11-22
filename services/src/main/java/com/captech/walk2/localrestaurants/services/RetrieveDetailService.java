package com.captech.walk2.localrestaurants.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.captech.walk2.localrestaurants.models.RestaurantDetail;
import com.captech.walk2.localrestaurants.models.Review;
import com.captech.walk2.localrestaurants.transformers.FactsToRestaurantDetailTransformer;
import com.captech.walk2.rottentomatoes.Celebrity;
import com.captech.walk2.rottentomatoes.TomatoeSource;
import com.captech.walk2.utils.FactualFactory;
import com.factual.driver.Factual;
import com.factual.driver.RowResponse;

@Component
public class RetrieveDetailService {

	@Autowired
	private FactualFactory factory;
	
	@Autowired
	private FactsToRestaurantDetailTransformer factualTransformer;
	
	@Autowired
	private TomatoeSource tomatoeSource;
	
	public RestaurantDetail get(String id) {
		
		Factual factual = factory.get();
		
		RowResponse facts =  factual.fetchRow("restaurants-us", id);
		RestaurantDetail detail = factualTransformer.transform(facts);
		Celebrity celeb = tomatoeSource.getCeleb();
		String reviewText = tomatoeSource.createReview(celeb.getMovieId());
		Review review = new Review();
		review.setReviewer(celeb.getName() + " (" + celeb.getMovieTitle() +")");
		review.setComment(reviewText);
		detail.getReviews().add(review);
		
		return detail;
	}
}

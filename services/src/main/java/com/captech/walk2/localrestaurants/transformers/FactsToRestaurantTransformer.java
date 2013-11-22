/**
 * 
 */
package com.captech.walk2.localrestaurants.transformers;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.captech.walk2.localrestaurants.models.Restaurant;
import com.captech.walk2.transformers.Transformer;

/**
 * @author Macon
 *
 */
@Component
public class FactsToRestaurantTransformer implements Transformer<Map<String, Object>, Restaurant> {

	@Override
	public Restaurant transform(Map<String, Object> factMap) {
		
		Restaurant restaurant = new Restaurant();
		
		// this is quite a horrible way to do things, but it's what we're going to do for now.
		try {
			restaurant.setId((String)factMap.get("factual_id"));
			restaurant.setName((String)factMap.get("name"));
	
			
			Object distance = factMap.get("$distance");
			if (distance != null)
				restaurant.setDistance(Double.parseDouble(distance.toString()));
			
			Object rating = factMap.get("rating");
			if (rating != null)
				restaurant.setRating(Double.parseDouble(rating.toString()));
		}
		catch (Exception ex) {
			// Just spit out the badness, and throw away the data.
			ex.printStackTrace();
			restaurant = null;
		}
		
		return restaurant;
	}

}

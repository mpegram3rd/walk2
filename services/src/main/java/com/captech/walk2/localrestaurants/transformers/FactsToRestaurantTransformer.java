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
public class FactsToRestaurantTransformer extends FactualTransformerHelper
										  implements Transformer<Map<String, Object>, Restaurant> {

	@Override
	public Restaurant transform(Map<String, Object> factMap) {
		
		Restaurant restaurant = new Restaurant();
		
		// this is quite a horrible way to do things, but it's what we're going to do for now.
		try {
			restaurant.setId(getString(factMap, "factual_id"));
			restaurant.setName(getString(factMap, "name"));
			restaurant.setDistance(getDouble(factMap, "$distance"));
			restaurant.setRating(getDouble(factMap, "rating"));
		}
		catch (Exception ex) {
			// Just spit out the badness, and throw away the data.
			ex.printStackTrace();
			restaurant = null;
		}
		
		return restaurant;
	}

}

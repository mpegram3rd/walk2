/**
 * 
 */
package com.captech.walk2.localrestaurants.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.captech.walk2.base.models.Status;
import com.captech.walk2.localrestaurants.models.LocalRestaurantsResponse;
import com.captech.walk2.localrestaurants.models.Restaurant;
import com.captech.walk2.localrestaurants.transformers.FactsToRestaurantTransformer;
import com.captech.walk2.utils.FactualFactory;
import com.factual.driver.Circle;
import com.factual.driver.Factual;
import com.factual.driver.Query;
import com.factual.driver.ReadResponse;

/**
 * @author Macon
 *
 */
@Component
public class RetrieveListService {

	@Autowired
	private FactualFactory factory;
	
	@Autowired
	private FactsToRestaurantTransformer transformer;
	
	public LocalRestaurantsResponse find(double latitude, double longitude, int radiusMeters) {
		
		LocalRestaurantsResponse response = null;
		
		try {
			Factual factual = factory.get();
			ReadResponse facts = factual.fetch("restaurants-us", new Query().within(new Circle(latitude, longitude, radiusMeters)));
			LocalRestaurantsResponse.Builder builder = new LocalRestaurantsResponse.Builder(Status.OK);
			if (facts != null) {
				
				// TODO This is a pretty sketchy way to parse.. need to clean up.
				for (Map<String, Object> factMap: facts.getData()) {
					
					Restaurant restaurant = transformer.transform(factMap);
					if (restaurant != null)
							builder.restaurant(restaurant);
				}
				response = builder.build();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace(); // sloppy but it's a hackathon afterall
			response = new LocalRestaurantsResponse.Builder(Status.SYSTEM_ERROR, ex.getMessage())
									.build();
		}
		
		return response;
		
	}
	
}

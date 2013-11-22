package com.captech.walk2.localrestaurants.transformers;

import org.springframework.stereotype.Component;

import com.captech.walk2.localrestaurants.models.RestaurantDetail;
import com.captech.walk2.transformers.Transformer;
import com.factual.driver.RowResponse;

@Component
public class FactsToRestaurantDetailTransformer implements Transformer<RowResponse, RestaurantDetail> {

	@Override
	public RestaurantDetail transform(RowResponse original) {
		
		
		return null;
	}

	
}

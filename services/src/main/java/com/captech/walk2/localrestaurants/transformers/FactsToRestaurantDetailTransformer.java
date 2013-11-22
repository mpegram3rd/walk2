package com.captech.walk2.localrestaurants.transformers;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.captech.walk2.localrestaurants.models.RestaurantDetail;
import com.captech.walk2.localrestaurants.models.Review;
import com.captech.walk2.transformers.Transformer;
import com.factual.driver.RowResponse;

@Component
public class FactsToRestaurantDetailTransformer extends FactualTransformerHelper
                                               implements Transformer<RowResponse, RestaurantDetail> {
	
	private static final String [] priceLabels = new String[] { "Cheap", "Reasonable", "Moderate", "Expensive", "Ouch!" };
	
	@Override
	public RestaurantDetail transform(RowResponse facts) {
		
		
		RestaurantDetail detail = null;
		
		if (facts != null) {
			detail = new RestaurantDetail();
			Map<String, Object> factMap = facts.first();
			
			detail.setId(getString(factMap, "factual_id"));
			detail.setName(getString(factMap, "name"));
			detail.setStreetAddress(getString(factMap, "address"));
			detail.setLocality(getString(factMap, "locality"));
			detail.setState(getString(factMap, "region"));
			detail.setPostalCode(getString(factMap, "postcode"));
			detail.setTelephone(getString(factMap, "telephone"));
			detail.setUrl(getString(factMap, "website"));
			
			// Rated on a scale of 1 to 5. 
			int priceScale = getInt(factMap, "priceScale");
			detail.setPriceScale(priceLabels[priceScale % 5]);
			
			detail.setLatitude(getDouble(factMap, "latitude"));
			detail.setLongitude(getDouble(factMap, "longitude"));
			
		}
		
		return detail;
	}

	
}

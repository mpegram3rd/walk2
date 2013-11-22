package com.captech.walk2.localrestaurants.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.captech.walk2.localrestaurants.models.RestaurantDetail;
import com.captech.walk2.localrestaurants.transformers.FactsToRestaurantDetailTransformer;
import com.captech.walk2.utils.FactualFactory;
import com.factual.driver.Factual;
import com.factual.driver.RowResponse;

@Component
public class RetrieveDetailService {

	@Autowired
	private FactualFactory factory;
	
	@Autowired
	private FactsToRestaurantDetailTransformer factualTransformer;
	
	public RestaurantDetail get(String id) {
		
		Factual factual = factory.get();
		
		RowResponse facts =  factual.fetchRow("restaurants-us", id);
		RestaurantDetail detail = factualTransformer.transform(facts);
		
		return detail;
	}
}

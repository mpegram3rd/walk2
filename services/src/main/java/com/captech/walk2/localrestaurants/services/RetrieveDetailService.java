package com.captech.walk2.localrestaurants.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.captech.walk2.localrestaurants.models.RestaurantDetail;
import com.captech.walk2.utils.FactualFactory;

@Component
public class RetrieveDetailService {

	@Autowired
	private FactualFactory factory;
	
	public RestaurantDetail get(String id) {
		
		return null;
	}

}

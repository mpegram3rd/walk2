/**
 * 
 */
package com.captech.walk2.localrestaurants.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.captech.walk2.base.models.BaseResponse;
import com.captech.walk2.base.models.Status;

/**
 * @author Macon
 *
 */
@XmlRootElement()
@XmlAccessorType( XmlAccessType.FIELD )
public class LocalRestaurantsResponse extends BaseResponse {

	private List<Restaurant> restaurants;
	
	// Making JAXB happy
	private LocalRestaurantsResponse() {};
	
	private LocalRestaurantsResponse(Builder builder) {
		super(builder);
		restaurants = Collections.unmodifiableList(builder.restaurants);
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	// Helps to create an immutable response object.
	public static class Builder extends BaseBuilder {
		
		private List<Restaurant> restaurants;
		
		public Builder(Status status) {
			super(status);
			restaurants = new ArrayList<Restaurant>();
		}
		
		public Builder(Status status, String additionalStatus) {
			super(status, additionalStatus);
		}
		
		public Builder restaurant(Restaurant restaurant) {
			if (restaurant != null) {
				restaurants.add(restaurant);
			}
			
			return this;
		}
		
		public LocalRestaurantsResponse build() {
			return new LocalRestaurantsResponse(this);
		}
	}
}

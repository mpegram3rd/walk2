package com.captech.walk2.localrestaurants.transformers;

import java.util.Map;

public abstract class FactualTransformerHelper {

	
	protected String getString(Map<String, Object> map, String key) {
		 
		return (map.get(key) != null ? map.get(key).toString() : "");
	}
	
	protected int getInt(Map<String, Object> map, String key) {
		
		return (map.get(key) != null ? Integer.valueOf(getString(map, key)) : 0);
	}
	
	protected double getDouble (Map<String, Object> map, String key) {
		
		return (map.get(key) != null ? Double.valueOf(getString(map, key)) : 0);
	}
}

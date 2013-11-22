/**
 * 
 */
package com.captech.walk2.utils;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.factual.driver.Factual;

/**
 * Use this class to grab a handle to the Factual APIs.  This 
 * provides support for hot-deployment/reconfiguration of the API key set.
 */
@Component
public class FactualFactory {
	
	@Autowired
	private Configuration config;
	
	public Factual get() {
		return new Factual(config.getString("factual.key"), config.getString("factual.secret"));
	}

}

package com.captech.walk2.utils;

import java.io.File;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.springframework.stereotype.Component;

@Component
public class CommonsConfigurationCompositeFactory {

	private String configFile;
	private CombinedConfiguration configuration;

	public CommonsConfigurationCompositeFactory () {}
	
	public Configuration getConfig() throws ConfigurationException {
		if (configuration == null) {
			DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
			builder.setFile(new File(getConfigFile()));
			configuration = builder.getConfiguration(true);
			configuration.setForceReloadCheck(true);
		}

		return configuration;
	}
	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
}
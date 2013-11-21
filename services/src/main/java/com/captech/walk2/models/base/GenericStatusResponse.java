package com.captech.walk2.models.base;

public class GenericStatusResponse extends Response {

	// JAXB requires a default constructor
	private GenericStatusResponse() {  };  
	
	private GenericStatusResponse(Builder builder) {
		super(builder);		
	}
	

	public static class Builder extends BaseBuilder {
		
		public Builder(Status status) {
			super(status);
		}
		
		public Builder(Status status, String additionalStatus) {
			super(status, additionalStatus);
		}
		
		public GenericStatusResponse build() {
			return new GenericStatusResponse(this);
		}
	}

}

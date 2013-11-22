package com.captech.walk2.base.models;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * @author mpegram
 *
 */
@XmlType
@XmlEnum(String.class)
public enum Status {     

	// For the moment I've aligned these with HTTP status code values, 
	// however the specific values are not terribly important.
	OK (200, "OK"),
	NOT_AUTHORIZED (401, "Not Authorized"),
	NO_DATA_FOUND (404, "No Data Found"),
	SYSTEM_ERROR (500, "Error"),
	BACKEND_UNAVAILABLE (503, "Backend Unavailable");
	
	private final int code;
	private final String message;
	
	private Status (int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public String toString() {
		return new StringBuilder(code).append(":").append(message).toString();
	}
}

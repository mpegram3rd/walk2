package com.captech.walk2.base.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseResponse {

	private Status status;
	private String additionalStatus;

	protected BaseResponse() { }; // jaxb is unhappy without a default constructor

	protected BaseResponse(BaseBuilder builder) {
		status = builder.status;
		additionalStatus = builder.additionalStatus;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return (status == null ? Status.OK : status); // default to an OK status.
	}

	/**
	 * @return the additionalStatus
	 */
	public String getAdditionalStatus() {
		return additionalStatus;
	}

	public static abstract class BaseBuilder {

		private final Status status;
		private String additionalStatus;

		public BaseBuilder(Status status) {
			this.status = status;
		}

		// TODO need unit test cases.
		public BaseBuilder(Status status, String additionalStatus) {
			this.status = status;
			this.additionalStatus = additionalStatus;
		}

		public abstract BaseResponse build();
	}

}

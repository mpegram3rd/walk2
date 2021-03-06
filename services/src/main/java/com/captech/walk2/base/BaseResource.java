/**
 * 
 */
package com.captech.walk2.base;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.captech.walk2.base.models.BaseResponse;
import com.captech.walk2.base.models.GenericStatusResponse;
import com.captech.walk2.base.models.Status;
import com.captech.walk2.base.models.GenericStatusResponse.Builder;


/**
 * @author Macon
 *
 */
public class BaseResource {

	public static final String XML_TYPE="XML";
	public static final String JSON_TYPE="JSON";
	
	/**
	 * Method returns a response builder mapped to the response and response type.
	 * This allows the caller to further "decorate" the response before calling the
	 * build() method.
	 * 
	 * @param resp
	 * @param type
	 * @return
	 */
	protected ResponseBuilder createInitialResponseBuilder(BaseResponse resp, String type) {
		
		ResponseBuilder builder = null;
		if (resp == null)
			builder = Response.serverError().entity(Status.SYSTEM_ERROR);
		else
			builder = Response.status(resp.getStatus().getCode()).entity(resp);

		return builder.type((XML_TYPE.equalsIgnoreCase(type) ? MediaType.APPLICATION_XML: MediaType.APPLICATION_JSON));
		
	}
	
	/**
	 * A simpler, cleaner response generator.
	 * 
	 * @param entity
	 * @param type
	 * @param statusValue
	 * @return
	 */
	protected Response createSimpleResponse(Object entity, String type, Status statusValue)  {
		
		ResponseBuilder builder = null;
		Response response = null;
		String mediaType = (XML_TYPE.equalsIgnoreCase(type) ? MediaType.APPLICATION_XML: MediaType.APPLICATION_JSON);
		int statusCode = (statusValue != null ? statusValue.getCode() : Status.SYSTEM_ERROR.getCode());
		
		if (entity == null) 
			response = Response.status(statusCode).type(mediaType).build();
		else
			response = Response.status(statusCode).type(mediaType).entity(entity).build();
		
		return response;
	}
	
	/**
	 * Convenience method will build default response type (JSON)
	 * @param resp
	 * @return
	 */
	protected Response buildResponse(BaseResponse resp) {
		return buildResponse(resp, null);
	}
	
	/**
	 * build the proper response object to send to the client
	 * @param resp
	 * @param type "XML" or "JSON"
	 * @return
	 */
	protected Response buildResponse(BaseResponse resp, String type) {
		return createInitialResponseBuilder(resp, type).build();
	}

	/**
	 * Creates an "Unauthroized" (401) response.
	 * 
	 * @param type
	 * @return
	 */
	protected Response createNotAuthorizedResponse(String type) {
		return buildResponse(new GenericStatusResponse.Builder(Status.NOT_AUTHORIZED).build(), type);
	}
	
	
}

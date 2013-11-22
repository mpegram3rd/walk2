/**
 * 
 */
package com.captech.walk2.localrestaurants.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.captech.walk2.base.BaseResource;
import com.captech.walk2.base.models.Status;
import com.captech.walk2.localrestaurants.models.LocalRestaurantsResponse;
import com.captech.walk2.localrestaurants.models.RestaurantDetail;
import com.captech.walk2.localrestaurants.services.RetrieveDetailService;
import com.captech.walk2.localrestaurants.services.RetrieveListService;

/**
 * @author Macon
 *
 */
@Component
@Path("/v1/restaurant")
public class LocalRestaurants extends BaseResource {
	
	@Autowired
	private Configuration config;
	
	@Autowired
	private RetrieveListService retrieveListSvc;
	
	@Autowired
	private RetrieveDetailService detailSvc;
	
	
	@GET @Path("/list/{latitude}/{longitude}")
	@Produces(value={"application/json", "application/xml"})
	public Response find(
			@QueryParam(value="type") @DefaultValue(BaseResource.JSON_TYPE) String type,
			@PathParam("latitude") double latitude,
			@PathParam("longitude") double longitude,
			@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException {
		
		System.out.println("Find matches with lat: " + latitude + " and long: " + longitude);
		LocalRestaurantsResponse  response = retrieveListSvc.find(latitude, longitude, 1600); // set radius to a mile for now. 
		
		return createInitialResponseBuilder(response, type).build();
	}
	
	@GET @Path("/{id}")
	@Produces(value={"application/json", "application/xml"})
	public Response get(
			@QueryParam(value="type") @DefaultValue(BaseResource.JSON_TYPE) String type,
			@PathParam("id") String id,
			@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException {
		
		System.out.println("Find a single restaurant with id: " + id);
		
		RestaurantDetail detail = null;
		Status status = null;
		try {
			detail = detailSvc.get(id);
			status = Status.OK;
		}
		catch (Exception ex) {
			status = Status.NO_DATA_FOUND;
		}
		
		return this.createSimpleResponse(detail, type, status);
	}	


}

/**
 * 
 */
package com.captech.walk2.resources;

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

import org.springframework.stereotype.Component;

import com.captech.walk2.resources.base.BaseResource;

/**
 * @author Macon
 *
 */
@Component
@Path("/v1/restaurant")
public class LocalRestaurants extends BaseResource {
	
	@GET @Path("/list/{latitude}/{longitude}")
	@Produces(value={"application/json", "application/xml"})
	public Response find(
			@QueryParam(value="type") @DefaultValue(BaseResource.JSON_TYPE) String type,
			@PathParam("latitude") String latitude,
			@PathParam("longitude") String longitude,
			@Context HttpServletRequest req, @Context HttpServletResponse resp) throws IOException {
		
		System.out.println("Find matches with lat: " + latitude + " and long: " + longitude);
		
		return null;
	}	

}

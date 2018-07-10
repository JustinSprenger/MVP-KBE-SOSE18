package de.htwBerlin.ai.kbe.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.auth.Auth;
import de.htwBerlin.ai.kbe.ifaces.UserDAO;


@Path("/auth")
public class AuthWebService {

	private UserDAO userDAO;

	@Inject
	public AuthWebService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@GET
	@Produces( MediaType.TEXT_PLAIN)
	public Response getUserToken(@QueryParam("userId") String userId) {		
		if(userDAO.isUserRegistred(userId))
		{
			return Response.status(Response.Status.OK).entity(Auth.generateToken(userId)).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
}

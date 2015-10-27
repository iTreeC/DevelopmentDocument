package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "service")
public class TestService {
	
	@GET
	@Path(value = "/{message}")
	@Produces(MediaType.APPLICATION_XML)
	public String echoService(@PathParam("message") String message) {
		return message;
	}
}

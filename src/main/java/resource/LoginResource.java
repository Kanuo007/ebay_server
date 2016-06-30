package resource;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import api.Welcome;
import io.dropwizard.jersey.params.NonEmptyStringParam;

// Author: Danni Li, created on June 28

@Path("/user-login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

  public LoginResource() {
  }

  @GET
  @Timed
  @Path("/{username}")
  public Welcome receiveUser(@PathParam("username") NonEmptyStringParam username) {
    if (username != null) {
      return new Welcome("Welcome, " + username);
    } else {
      return new Welcome("Welcome Guest");
    }
  }
}

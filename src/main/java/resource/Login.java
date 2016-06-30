package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import api.Welcome;
import core.User;

// Author: Danni Li, created on June 28

@Path("/user-login")
@Produces(MediaType.APPLICATION_JSON)
public class Login {
  private String username;
  private String password;

  public Login(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @GET
  @Timed
  @Path("/{username}")
  @Produces(MediaType.APPLICATION_JSON)
  public Welcome receiveUser(@PathParam("username") String username) {
    if (username != null) {
      return new Welcome("Welcome, " + username);
    } else {
      return new Welcome("Welcome Guest");
    }
  }
}

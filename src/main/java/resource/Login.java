package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
  @Path("/")
  @Produces(MediaType.TEXT_PLAIN)
  public Welcome receiveUser(@QueryParam("user") Optional<User> user) {
    if (user.isPresent()) {
      User registeredUser = user.get();
      return new Welcome("Welcome, " + registeredUser.getName());
    } else {
      return new Welcome("Welcome Guest");
    }
  }
}

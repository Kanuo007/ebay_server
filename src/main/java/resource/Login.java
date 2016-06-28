package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;

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
  @Path("/user")
  @Produces(MediaType.TEXT_PLAIN)
  public String receiveUser(@QueryParam("user") Optional<User> user) {
    if (user.isPresent()) {
      User registeredUser = user.get();
      return "Welcome, " + registeredUser.getName();
    } else {
      return "User doesn't exist";
    }
  }
}

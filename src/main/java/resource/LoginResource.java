package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import core.User;
import db.UserDao;
import io.dropwizard.auth.Auth;

// Author: Danni Li, created on June 28

@Path("/user-login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

  private UserDao userDao;

  public LoginResource(UserDao userDao) {
    this.userDao = userDao;
  }

  @GET
  @Timed
  @Path("/{username}")
  public String checkUser(@Auth User user) {
    if (this.userDao.UserNamePasswordMatch(user.getUser_name(), user.getUser_password())) {
      return new String("Sucessfully login");
    } else {
      return new String("UserName and Password does not match. Please try again");
    }
  }
}

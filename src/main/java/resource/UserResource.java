package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import api.Register;
import core.User;
import db.UserDao;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;


@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  private UserDao userDao;
  private static Logger logger = LoggerFactory.getLogger(UserResource.class);

  public UserResource(UserDao userDao) {
    this.userDao = userDao;
  }

  @POST
  @Timed
  @UnitOfWork
  @Path("/log_in")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public String login(@Auth User user) {
    return "Login Successfully! Welcome " + user.getUser_name();
  }

  @POST
  @Path("/register")
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public Register register(User user) {
    Register r;
    if (!this.userDao.findUserByName(user.getUser_name()).isPresent()) {
      // If user doesn't exist
      this.userDao.createUser(user);
      r = new Register(user.getUser_name(), user.getUser_email(), user.getUser_password(),
          "Success");
    } else {
      r = new Register("", "", "", "Failure : user name alrady exists");
    }
    return r;
  }
}

package resource;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import io.dropwizard.jersey.params.LongParam;


@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  private UserDao userDao;
  private static Logger logger = LoggerFactory.getLogger(UserResource.class);

  public UserResource(UserDao userDao) {
    this.userDao = userDao;
  }


  @GET
  @UnitOfWork
  public User getUser(@PathParam("userId") LongParam userId) {
    return findUser(userId.get());
  }

  private User findUser(Long userId) {
    return this.userDao.findUserByID(userId)
        .orElseThrow(() -> new NotFoundException("User does not exist"));
  }


  @GET
  @Timed
  @UnitOfWork
  @Path("/log_in")
  @Consumes(MediaType.APPLICATION_JSON)
  public String login(@Auth User user) {
    if (this.userDao.UserNamePasswordMatch(user.getUser_name(), user.getUser_password())) {
      return new String("Login Successfully");
    } else {
      throw new NotFoundException("User does't exist or password and username doesn't match");
    }
  }

  @POST
  @Path("/register")
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public Register register(User user) {
    Register r;
    Optional<User> op = this.userDao.findUserByName(user.getUser_name());
    if (!op.isPresent()) {
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

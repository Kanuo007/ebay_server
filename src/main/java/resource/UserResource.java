package resource;

import java.util.Optional;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Api
public class UserResource {
  private UserDao userDao;
  private static Logger logger = LoggerFactory.getLogger(UserResource.class);

  public UserResource(UserDao userDao) {
    this.userDao = userDao;
  }

  // @GET
  // @UnitOfWork
  // @ApiOperation(value = "get user", notes = "This return the user by given user_id")
  // @ApiResponses(value = {@ApiResponse(code = 400, message = "invalid ID", response =
  // User.class)})
  // public User getUser(@ApiParam(value = "user_id to look for an user",
  // required = true) @PathParam("userId") LongParam userId) {
  // return findUser(userId.get());
  // }
  //
  // private User findUser(Long userId) {
  // return this.userDao.findUserByID(userId)
  // .orElseThrow(() -> new NotFoundException("User does not exist"));
  // }

  @POST
  @Timed
  @UnitOfWork
  @Path("/log_in")
  @Consumes(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "user login",
      notes = "This return a string to indicate if a user login successfully or not")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "password and username doesn't match",
          response = String.class),
      @ApiResponse(code = 404, message = "user doesn't exist", response = String.class)})
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

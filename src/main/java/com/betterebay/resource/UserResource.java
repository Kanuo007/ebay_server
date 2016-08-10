package com.betterebay.resource;

import com.betterebay.api.Register;
import com.codahale.metrics.annotation.Timed;
import com.betterebay.core.User;
import com.betterebay.db.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;


@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="/user", description = "This is the user com.betterebay.api that either register or log in.")
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
  @ApiOperation(value = "user login",
          authorizations = {@Authorization(value="UserBasicAuth")},
          notes = "This return a string to indicate if a user login successfully or not")
  @ApiResponses(value = {
          @ApiResponse(code = 401, message = "Password doesn't match with User: <user>.",
                  response = String.class),
          @ApiResponse(code = 404, message = "User: <user> doesn't exist.", response = String.class),
          @ApiResponse(code = 200, message = "Login Successfully! Welcome <user>", response = String.class)
  })
  @Produces(MediaType.TEXT_PLAIN)
  public Response login(@Auth User user) {
    if(user.getUser_password().equals("Incorrect User")){
      return Response.status(Response.Status.NOT_FOUND).entity("User: " + user.getUser_name() + " doesn't exist.").build();
    }else if(user.getUser_password().equals("Incorrect Password")){
      return Response.status(Response.Status.UNAUTHORIZED).entity("Password doesn't match with User: " + user.getUser_name()).build();
    }
    return Response.ok("Login Successfully! Welcome " + user.getUser_name() + " with id " + user.getId(),
            MediaType.TEXT_PLAIN).build();
  }

  @POST
  @Path("/register")
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "user register",
          notes = "This return an Register Object in JSON format")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message="Successfully created User.", response = Register.class),
          @ApiResponse(code = 200, message="Failure : user name already exists.", response = Register.class)
  })
  public Register register(User user) {
    Register r;
    Optional<User> op = this.userDao.findUserByName(user.getUser_name());
    if (!op.isPresent()) {
      // If user doesn't exist
      this.userDao.createUser(user);
      r = new Register(user.getUser_name(), user.getUser_email(), user.getUser_password(), "Success");

    } else {
      r = new Register("", "", "", "Failure : user name already exists");
    }
    return r;
  }
}

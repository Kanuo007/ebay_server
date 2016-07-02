package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import api.Register;
import core.User;
import db.UserDao;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
public class RegisterResource {
  private UserDao userDao;
  private static Logger logger = LoggerFactory.getLogger(RegisterResource.class);

  public RegisterResource(UserDao userDao) {
    this.userDao = userDao;
  }


  @POST
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public Register register(@QueryParam("name") String name, @QueryParam("email") String email,
      @QueryParam("password") String password) {
    Register r;
    if (!this.userDao.findUserByName(name).isPresent()) {
      // If user doesn't exist
      User user = new User(name, password, email);
      this.userDao.createUser(user);
      r = new Register(name, email, password, "Success");
    } else {
      r = new Register("", "", "", "Failure");
    }
    return r;
  }
}

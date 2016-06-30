package resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import api.Register;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
public class RegisterResource {
  private static Logger logger = LoggerFactory.getLogger(RegisterResource.class);

  public RegisterResource() {}

  @POST
  @Timed
  @UnitOfWork
  public Register register(@QueryParam("name") String name, @QueryParam("email") String email, @QueryParam("password") String password) {
    return new Register(name, email, password);
  }
}

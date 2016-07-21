package resource;

import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

    @GET
    @Timed
    @Path("/log_in")
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(@Auth User user) {
        if (this.userDao.UserNamePasswordMatch(user.getUser_name(), user.getUser_password())) {
            return user;
        } else {
            return new User("invalid user name", "invalid password", "invalid email");
        }
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
            r = new Register(user.getUser_name(), user.getUser_email(), user.getUser_password(), "Success");
        } else {
            r = new Register("", "", "", "Failure");
        }
        return r;
    }
}
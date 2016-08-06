package auth;

import com.google.common.base.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;

import core.User;
import db.UserDao;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class UserAuthenticator implements Authenticator<BasicCredentials, User> {

  private UserDao userDao;
  private SessionFactory sessionFactory;

  public UserAuthenticator(UserDao userDao, SessionFactory sessionFactory) {
    this.userDao = userDao;
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
    Session session = this.sessionFactory.openSession();
    ManagedSessionContext.bind(session);
    java.util.Optional<User> user = this.userDao.findUserByName(credentials.getUsername());
    if (user.isPresent()) {
      if(user.get().getUser_password().equals(credentials.getPassword())) {
        return Optional.fromNullable(user.get());
      }else{
        return Optional.fromNullable(new User(credentials.getUsername(), "Incorrect Password", "Incorrect Password"));
      }
    }
    return Optional.fromNullable(new User(credentials.getUsername(), "Incorrect User", "Incorrect User"));
  }
}

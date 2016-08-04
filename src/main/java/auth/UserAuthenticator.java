package auth;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;

import com.google.common.base.Optional;

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
    Optional<User> user =
        Optional.fromNullable(this.userDao.findUserByName(credentials.getUsername()).get());
    if (user.isPresent() && user.get().getUser_password().equals(credentials.getPassword())) {
      return user;
    }
    return Optional.absent();
  }
}

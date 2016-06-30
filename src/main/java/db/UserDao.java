package db;

import java.util.List;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;

import core.User;
import io.dropwizard.hibernate.AbstractDAO;

public class UserDao extends AbstractDAO<User> {

  public UserDao(SessionFactory factory) {
    super(factory);
  }

  public Optional<User> findUserByID(Long id) {
    return Optional.fromNullable(get(id));
  }

  public User createUser(User user) {
    return persist(user);
  }

  public List<User> findAllUser() {
    return list(namedQuery(""));
  }
}

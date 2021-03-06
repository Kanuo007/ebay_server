package com.betterebay.db;

import com.betterebay.core.User;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;

public class UserDao extends AbstractDAO<User> {

  public UserDao(SessionFactory factory) {
    super(factory);
  }

  public Optional<User> findUserByID(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Optional<User> findUserByName(String name) {
    return Optional.ofNullable(
        (User) (namedQuery("com.betterebay.core.user.findUserByName").setParameter("name", name).uniqueResult()));
  }

  public Optional<User> findUserByEmail(String email) {
    return Optional.ofNullable((User) (namedQuery("com.betterebay.core.user.findUserByEmail")
        .setParameter("email", email).uniqueResult()));
  }

  public Optional<User> findUserByPassword(String password) {
    return Optional.ofNullable((User) (namedQuery("com.betterebay.core.user.findUserByPassword")
        .setParameter("password", password).uniqueResult()));
  }

  public User createUser(User user) {
    return persist(user);
  }


  @SuppressWarnings("unchecked")
  public List<User> findAllUser() {
    return namedQuery("").list();
  }

  public boolean UserNamePasswordMatch(String name, String password) {
    if (!(Optional.ofNullable(
        (User) (namedQuery("com.betterebay.core.user.findUserByName").setParameter("name", name)).uniqueResult()))
            .isPresent()) {
      return false;
    } else {
      return (Optional.ofNullable(
          (User) (namedQuery("com.betterebay.core.user.findUserByName").setParameter("name", name).uniqueResult()))
          .get().getUser_password().equals(password));
    }
  }
}

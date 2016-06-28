package db;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import core.User;
import io.dropwizard.hibernate.AbstractDAO;

public class UserDao extends AbstractDAO<User> {

    public UserDao(SessionFactory factory){
        super(factory);
    }

    public Optional<User> findUserByID(Long id){
        return Optional.ofNullable(get(id));
    }

    public User createUser(User user){
        return persist(user);
    }

    public List<User> findAllUser(){
        return list(namedQuery(""));
    }
}

package db;

import org.hibernate.SessionFactory;

import java.util.List;
import core.User;
import java.util.Optional;
import io.dropwizard.hibernate.AbstractDAO;

public class PersonDao extends AbstractDAO<User> {

    public PersonDao(SessionFactory factory){
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

package auth;

import com.google.common.base.Optional;

import core.User;
import db.UserDao;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class UserAuthenticator implements Authenticator<BasicCredentials, User>{

    private UserDao userDao;

    public UserAuthenticator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        Optional<User> user = userDao.findUserByName(credentials.getUsername());
        if(user.isPresent() && user.get().getUser_password().equals(credentials.getPassword())){
            return user;
        }
        return Optional.absent();
    }
}

package com.betterebay.auth;

import com.betterebay.core.User;

import io.dropwizard.auth.Authorizer;


public class UserAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role){
        return false;
    }
}

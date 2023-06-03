package com.restfulmovies.restfulmovies.exception.notfound;

import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String username) {
        super("User", username);
    }
}

package com.restfulmovies.restfulmovies.exception.alreadyexist;

import com.restfulmovies.restfulmovies.exception.AlreadyExistException;

public class UserAlreadyExistException extends AlreadyExistException {

    public UserAlreadyExistException(String value) {
        super("User", value);
    }
}

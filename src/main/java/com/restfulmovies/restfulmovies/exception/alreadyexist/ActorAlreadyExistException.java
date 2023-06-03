package com.restfulmovies.restfulmovies.exception.alreadyexist;

import com.restfulmovies.restfulmovies.exception.AlreadyExistException;
import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class ActorAlreadyExistException extends AlreadyExistException {

    public ActorAlreadyExistException(String value) {
        super("Actor", value);
    }
}

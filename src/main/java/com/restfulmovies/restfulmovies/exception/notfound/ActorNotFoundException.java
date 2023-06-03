package com.restfulmovies.restfulmovies.exception.notfound;

import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class ActorNotFoundException extends NotFoundException {

    public ActorNotFoundException(String value) {
        super("Actor", value);
    }
}

package com.restfulmovies.restfulmovies.exception.alreadyexist;

import com.restfulmovies.restfulmovies.exception.AlreadyExistException;
import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class MovieAlreadyExistException extends AlreadyExistException {

    public MovieAlreadyExistException(String value) {
        super("Movie", value);
    }
}

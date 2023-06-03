package com.restfulmovies.restfulmovies.exception.alreadyexist;

import com.restfulmovies.restfulmovies.exception.AlreadyExistException;
import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class GenreAlreadyExistException extends AlreadyExistException {

    public GenreAlreadyExistException(String value) {
        super("Genre", value);
    }
}

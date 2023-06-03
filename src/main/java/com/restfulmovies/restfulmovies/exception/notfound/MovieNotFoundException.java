package com.restfulmovies.restfulmovies.exception.notfound;

import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class MovieNotFoundException extends NotFoundException {

    public MovieNotFoundException(String value) {
        super("Movie", value);
    }
}

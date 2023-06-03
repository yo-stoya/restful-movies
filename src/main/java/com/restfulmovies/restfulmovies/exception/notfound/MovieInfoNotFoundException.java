package com.restfulmovies.restfulmovies.exception.notfound;

import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class MovieInfoNotFoundException extends NotFoundException {

    public MovieInfoNotFoundException(String value) {
        super("Movie info", value);
    }
}

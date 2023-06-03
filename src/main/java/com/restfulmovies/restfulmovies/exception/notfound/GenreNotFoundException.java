package com.restfulmovies.restfulmovies.exception.notfound;

import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class GenreNotFoundException extends NotFoundException {

    public GenreNotFoundException(String value) {
        super("Genre", value);
    }
}

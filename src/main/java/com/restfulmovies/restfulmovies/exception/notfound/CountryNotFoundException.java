package com.restfulmovies.restfulmovies.exception.notfound;

import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class CountryNotFoundException extends NotFoundException {

    public CountryNotFoundException(String value) {
        super("Country", value);
    }
}

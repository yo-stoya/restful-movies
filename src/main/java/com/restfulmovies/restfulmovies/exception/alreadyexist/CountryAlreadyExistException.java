package com.restfulmovies.restfulmovies.exception.alreadyexist;

import com.restfulmovies.restfulmovies.exception.AlreadyExistException;
import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class CountryAlreadyExistException extends AlreadyExistException {

    public CountryAlreadyExistException(String value) {
        super("Country", value);
    }
}

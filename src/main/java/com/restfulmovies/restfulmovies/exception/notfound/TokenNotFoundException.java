package com.restfulmovies.restfulmovies.exception.notfound;

import com.restfulmovies.restfulmovies.exception.NotFoundException;

public class TokenNotFoundException extends NotFoundException {

    public TokenNotFoundException(String token) {
        super("Token", token.substring(0,5));
    }
}

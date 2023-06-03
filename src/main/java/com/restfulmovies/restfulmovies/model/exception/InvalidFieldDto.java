package com.restfulmovies.restfulmovies.model.exception;

public record InvalidFieldDto(
        String property,
        String message
) {

}

package com.restfulmovies.restfulmovies.exception;

import com.restfulmovies.restfulmovies.model.exception.ExceptionDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyExistException extends RuntimeException {

    private final ExceptionDto exceptionDto;

    public AlreadyExistException(String entity, String value) {
        String message = String.format("%s {%s} already exist", entity, value);
        this.exceptionDto = toDto(message);
    }

    private ExceptionDto toDto(String message) {
        return new ExceptionDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                message);
    }
}

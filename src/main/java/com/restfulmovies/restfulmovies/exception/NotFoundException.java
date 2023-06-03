package com.restfulmovies.restfulmovies.exception;

import com.restfulmovies.restfulmovies.model.exception.ExceptionDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

    private final ExceptionDto exceptionDto;

    public NotFoundException(String entity, String value) {
        this.exceptionDto = toDto(String.format("%s {%s} not found", entity, value));
    }

    private ExceptionDto toDto(String message) {
        return new ExceptionDto(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                message);
    }
}

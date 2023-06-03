package com.restfulmovies.restfulmovies.model.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class InvalidFieldExceptionDto extends ExceptionDto{
    private final List<InvalidFieldDto> fields;

    public InvalidFieldExceptionDto(Integer status, HttpStatus error, String message, List<InvalidFieldDto> fields) {
        super(status, error, message);
        this.fields = fields;
    }
}

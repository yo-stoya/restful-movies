package com.restfulmovies.restfulmovies.model.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
@RequiredArgsConstructor
public class ExceptionDto {

    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();

    private final Integer status;

    private final HttpStatus error;

    private String path;

    private final String message;
}

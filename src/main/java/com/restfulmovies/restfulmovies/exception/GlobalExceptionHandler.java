package com.restfulmovies.restfulmovies.exception;

import com.restfulmovies.restfulmovies.model.exception.ExceptionDto;
import com.restfulmovies.restfulmovies.model.exception.InvalidFieldDto;
import com.restfulmovies.restfulmovies.model.exception.InvalidFieldExceptionDto;
import com.restfulmovies.restfulmovies.model.mapper.FieldErrorMapper;
import com.restfulmovies.restfulmovies.model.mapper.ViolationMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private static final String INVALID_FIELDS = "One or more invalid fields.";
    private final FieldErrorMapper fieldErrorMapper;
    private final ViolationMapper violationMapper;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        var exceptionDto = ex.getExceptionDto().setPath(request.getServletPath());
        return ResponseEntity.status(exceptionDto.getError()).body(exceptionDto);
    }


    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ExceptionDto> handleAlreadyExistException(AlreadyExistException ex, HttpServletRequest request) {
        var exceptionDto = ex.getExceptionDto().setPath(request.getServletPath());
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDto> handleConstraintViolationException(ConstraintViolationException ex,
                                                                           HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new InvalidFieldExceptionDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                INVALID_FIELDS,
                mapViolatedFields(ex)
        ).setPath(request.getServletPath()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionDto> handleBindException(BindException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new InvalidFieldExceptionDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                INVALID_FIELDS,
                mapErrorFields(ex)
        )
                .setPath(request.getServletPath()));
    }

    private List<InvalidFieldDto> mapErrorFields(BindException ex) {
        return ex.getFieldErrors().stream().map(fieldErrorMapper::toDto).collect(Collectors.toList());
    }

    private List<InvalidFieldDto> mapViolatedFields(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(violationMapper::toDto).collect(Collectors.toList());
    }


}

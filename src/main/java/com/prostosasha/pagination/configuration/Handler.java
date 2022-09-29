package com.prostosasha.pagination.configuration;

import com.prostosasha.pagination.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handle(ValidationException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

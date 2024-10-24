package com.talkative.exception.handler;

import com.talkative.exception.ErrorResponse;
import com.talkative.exception.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice

public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(UsernameNotFoundException ex) {
        logger.error("Resource not found: ", ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

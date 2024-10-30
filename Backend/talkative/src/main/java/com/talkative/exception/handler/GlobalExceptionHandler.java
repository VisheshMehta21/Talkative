package com.talkative.exception.handler;

import com.talkative.exception.ErrorResponse;
import com.talkative.exception.EmailNotFoundException;
import com.talkative.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(EmailNotFoundException ex) {
        log.error("Email not found : ", ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error("User Already Exists : ", ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("In Bad Credentials Handler: ", ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), "Invalid email or password.", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }


}

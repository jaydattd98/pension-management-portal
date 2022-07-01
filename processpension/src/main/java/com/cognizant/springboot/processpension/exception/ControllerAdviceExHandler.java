package com.cognizant.springboot.processpension.exception;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

/**
 * Class ControllerAdviceExHandler
 *
 * This class help to catch all exception in application
 * and will populate proper output in response
 *
 * @author 841771 jaydatt
 */
@Slf4j
@ControllerAdvice
public class ControllerAdviceExHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ControllerAdviceExHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleEmptyInput(NullPointerException exception) {
        log.error("exception occured, Input field is null " + exception.getMessage());
        return new ResponseEntity<String>("Input field is null, please check the input again",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoElementFound(NoSuchElementException exception) {
        log.error("exception occurred, " + exception.getMessage());
        return new ResponseEntity<String>( exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InternalException.class,RestClientException.class})
    public ResponseEntity<String> handleInternalException(InternalException exception) {
        log.error("exception occured, " + exception.getMessage());
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<String> handleAuthEx(AuthenticationCredentialsNotFoundException exception) {
        log.error("exception occured, " + exception.getMessage());
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }

}

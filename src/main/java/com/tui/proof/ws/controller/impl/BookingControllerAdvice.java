package com.tui.proof.ws.controller.impl;

import com.tui.proof.ws.exception.AvailabilityRequestNotValidException;
import com.tui.proof.ws.exception.HolderRequestNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class BookingControllerAdvice {

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<String> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        log.error("HttpMediaTypeNotSupportedException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("The received request body is not correct, error: %s",
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AvailabilityRequestNotValidException.class})
    public ResponseEntity<String> handleAvailabilityRequestNotValidException(AvailabilityRequestNotValidException ex) {
        log.error("AvailabilityRequestNotValidException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Some parameters in the request are not correct, error: %s",
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HolderRequestNotValidException.class})
    public ResponseEntity<String> handleHolderRequestNotValidException(HolderRequestNotValidException ex) {
        log.error("AvailabilityRequestNotValidException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Some parameters in the request are not correct, error: %s",
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("DateTimeParseException: {}", ex.getMessage());
        return new ResponseEntity<>("The dates in the requests must follow the pattern: yyyy-MM-dd",
                HttpStatus.BAD_REQUEST);
    }


}

package com.tui.proof.ws.controller.impl;

import com.tui.proof.ws.exception.*;
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

    @ExceptionHandler(value = {FlightRequestNotValidException.class})
    public ResponseEntity<String> handleFlightRequestNotValidException(FlightRequestNotValidException ex) {
        log.error("AvailabilityRequestNotValidException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Some parameters in the request are not correct, error: %s",
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmailNotValidException.class})
    public ResponseEntity<String> handleEmailNotValidException(EmailNotValidException ex) {
        log.error("AvailabilityRequestNotValidException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Some parameters in the request are not correct, error: %s",
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ReservationNotExistException.class})
    public ResponseEntity<String> handleReservationNotExistException(ReservationNotExistException ex) {
        log.error("AvailabilityRequestNotValidException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("There are no reservation for email: %s",
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AvailabilityFlightExpiredException.class})
    public ResponseEntity<String> handleAvailabilityFlightExpiredException(AvailabilityFlightExpiredException ex) {
        log.error("AvailabilityRequestNotValidException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("%s", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("DateTimeParseException: {}", ex.getMessage());
        return new ResponseEntity<>("The request cannot be parsed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        log.error("UnauthorizedException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Unauthorized: %s", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
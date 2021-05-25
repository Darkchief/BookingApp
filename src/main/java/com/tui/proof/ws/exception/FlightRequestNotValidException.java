package com.tui.proof.ws.exception;

public class FlightRequestNotValidException extends RuntimeException {

    public FlightRequestNotValidException(String errorMessage) {
        super(errorMessage);
    }

}

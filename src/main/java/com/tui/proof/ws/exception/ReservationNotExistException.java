package com.tui.proof.ws.exception;

public class ReservationNotExistException extends RuntimeException {

    public ReservationNotExistException(String errorMessage) {
        super(errorMessage);
    }

}

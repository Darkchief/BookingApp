package com.tui.proof.ws.exception;

public class ReservationCodeNotValidException extends RuntimeException {

    public ReservationCodeNotValidException(String errorMessage) {
        super(errorMessage);
    }

}

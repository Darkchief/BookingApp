package com.tui.proof.ws.exception;

public class EmailNotValidException extends RuntimeException {

    public EmailNotValidException(String errorMessage) {
        super(errorMessage);
    }

}

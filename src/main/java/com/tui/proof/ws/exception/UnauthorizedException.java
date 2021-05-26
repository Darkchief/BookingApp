package com.tui.proof.ws.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
    }

}

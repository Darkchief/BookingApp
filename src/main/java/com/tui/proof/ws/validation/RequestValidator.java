package com.tui.proof.ws.validation;

public interface RequestValidator<T> {

    String validate(T request);
}

package com.tui.proof.ws.validation;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * This is the validation factory, contains alla the request validation necessary
 */
@Data
@Accessors(chain = true)
public class RequestValidatorFactory<T> {

    Map<Class<RequestValidator<T>>, RequestValidator<T>> validators;
}

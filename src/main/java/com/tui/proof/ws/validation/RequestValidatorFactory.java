package com.tui.proof.ws.validation;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class RequestValidatorFactory {

    Map<Class, RequestValidator> validators;
}

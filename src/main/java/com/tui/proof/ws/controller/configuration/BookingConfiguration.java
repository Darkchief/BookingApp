package com.tui.proof.ws.controller.configuration;

import com.tui.proof.ws.validation.RequestValidator;
import com.tui.proof.ws.validation.RequestValidatorFactory;
import com.tui.proof.ws.validation.impl.AvailabilityRequestValidator;
import com.tui.proof.ws.validation.impl.HolderRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BookingConfiguration {

    @Bean
    public RequestValidatorFactory requestValidatorFactory() {
        Map<Class, RequestValidator> validators = new HashMap<>();
        validators.put(AvailabilityRequestValidator.class, new AvailabilityRequestValidator());
        validators.put(HolderRequestValidator.class, new HolderRequestValidator());

        return new RequestValidatorFactory().setValidators(validators);
    }
}

package com.tui.proof.ws.configuration;

import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.validation.RequestValidator;
import com.tui.proof.ws.validation.RequestValidatorFactory;
import com.tui.proof.ws.validation.impl.AvailabilityRequestValidator;
import com.tui.proof.ws.validation.impl.FlightRequestValidator;
import com.tui.proof.ws.validation.impl.HolderRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * This is the application configuration, where the beans are defined
 */
@Configuration
public class BookingConfiguration {

    @Bean
    public RequestValidatorFactory requestValidatorFactory(RequestValidator availabilityRequestValidator,
                                                           RequestValidator holderRequestValidator,
                                                           RequestValidator flightRequestValidator) {
        return new RequestValidatorFactory().setValidators(
                Map.of(
                        availabilityRequestValidator.getClass(), availabilityRequestValidator,
                        holderRequestValidator.getClass(), holderRequestValidator,
                        flightRequestValidator.getClass(), flightRequestValidator
                ));
    }

    @Bean
    public RequestValidator<AvailabilityRequest> availabilityRequestValidator() {
        return new AvailabilityRequestValidator();
    }

    @Bean
    public RequestValidator<HolderRequest> holderRequestValidator() {
        return new HolderRequestValidator();
    }

    @Bean
    RequestValidator<FlightRequest> flightRequestValidator() {
        return new FlightRequestValidator();
    }
}
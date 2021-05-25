package com.tui.proof.ws.validation.impl;

import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.validation.RequestValidator;
import org.apache.commons.lang3.StringUtils;

public class FlightRequestValidator implements RequestValidator<FlightRequest> {


    @Override
    public String validate(FlightRequest request) {

        StringBuilder builder = new StringBuilder();

        if (StringUtils.isBlank(request.getEmail())) {
            builder.append("\n- The email must not be null");
        }
        if (request.getFlightNumber() == null) {
            builder.append("\n- The flight number name must not be null");
        }

        return builder.toString();
    }
}

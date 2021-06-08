package com.tui.proof.ws.validation.impl;

import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.validation.RequestValidator;
import org.apache.commons.lang3.StringUtils;

public class FlightRequestValidator implements RequestValidator<FlightRequest> {


    @Override
    public String validate(FlightRequest request) {

        StringBuilder builder = new StringBuilder();

        String reservationCode = request.getReservationCode();
        if (StringUtils.isBlank(reservationCode)) {
            builder.append("\n- The reservation code must not be null");
        } else {
            try {
                Long.parseLong(reservationCode);
            } catch(NumberFormatException ex) {
                builder.append("\n- The reservation code must be a numeric value");
            }
        }
        if (request.getFlightNumber() == null) {
            builder.append("\n- The flight number name must not be null");
        }

        return builder.toString();
    }
}

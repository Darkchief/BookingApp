package com.tui.proof.ws.validation.impl;

import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.validation.RequestValidator;
import org.apache.commons.lang3.StringUtils;

public class AvailabilityRequestValidator implements RequestValidator<AvailabilityRequest> {

    @Override
    public String validate(AvailabilityRequest request) {

        StringBuilder builder = new StringBuilder();
        if (StringUtils.isBlank(request.getOriginAirport())) {
            builder.append("\n- Origin airport must not be null");
        }

        if (StringUtils.isBlank(request.getDestinationAirport())) {
            builder.append("\n- Destination airport must not be null");
        }

        if (request.getFrom() == null) {
            builder.append("\n- From must not be null");
        }

        if (request.getTo() == null) {
            builder.append("\n- To must not be null");
        }

        if (request.getPaxes() == null) {
            builder.append("\n- Paxes must not be null");
        } else {

            if (request.getPaxes().getInfants() == null) {
                builder.append("\n- Infants must not be null");
            }

            if (request.getPaxes().getChildren() == null) {
                builder.append("\n- Children must not be null");
            }

            if (request.getPaxes().getAdults() == null) {
                builder.append("\n- Adults must not be null");
            }
        }

        return builder.toString();

    }
}

package com.tui.proof.ws.validation.impl;

import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.validation.RequestValidator;
import org.apache.commons.lang3.StringUtils;

public class HolderRequestValidator implements RequestValidator<HolderRequest> {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public String validate(HolderRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append("false");

        return builder.toString();

    }
}

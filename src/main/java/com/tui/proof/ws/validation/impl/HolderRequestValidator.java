package com.tui.proof.ws.validation.impl;

import com.tui.proof.ws.model.booking.Holder;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.validation.RequestValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class HolderRequestValidator implements RequestValidator<HolderRequest> {


    @Override
    public String validate(HolderRequest request) {

        StringBuilder builder = new StringBuilder();

        if (request.getHolder() == null) {
            builder.append("\n- The holder must not be null");
        } else {
            Holder holder = request.getHolder();
            if (StringUtils.isBlank(holder.getName())) {
                builder.append("\n- The name must not be null");
            }
            if (StringUtils.isBlank(holder.getLastName())) {
                builder.append("\n- The last name must not be null");
            }
            if (StringUtils.isBlank(holder.getAddress())) {
                builder.append("\n- The address must not be null");
            }
            if (StringUtils.isBlank(holder.getPostalCode())) {
                builder.append("\n- The postalCode must not be null");
            }
            if (StringUtils.isBlank(holder.getCountry())) {
                builder.append("\n- The country must not be null");
            }
            if (StringUtils.isBlank(holder.getEmail())) {
                builder.append("\n- The email must not be null");
            }
            if (CollectionUtils.isEmpty(holder.getTelephones())) {
                builder.append("\n- The telephones list must not be empty");
            }
        }
        return builder.toString();
    }
}

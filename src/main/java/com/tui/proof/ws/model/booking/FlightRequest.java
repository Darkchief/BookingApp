package com.tui.proof.ws.model.booking;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FlightRequest {

    private String email;
    private Long flightNumber;
}

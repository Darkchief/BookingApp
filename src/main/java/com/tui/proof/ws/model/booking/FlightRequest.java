package com.tui.proof.ws.model.booking;

import com.tui.proof.ws.model.availability.Flight;
import lombok.Data;

import java.util.List;

@Data
public class FlightRequest {

    private String email;
    private String flightNumber;
}

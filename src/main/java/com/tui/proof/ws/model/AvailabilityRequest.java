package com.tui.proof.ws.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailabilityRequest {

    private String originAirport;
    private String destinationAirport;
    private LocalDate from;
    private LocalDate to;
    private Paxes paxes;
}

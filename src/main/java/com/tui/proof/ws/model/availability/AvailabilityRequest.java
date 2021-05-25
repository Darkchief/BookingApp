package com.tui.proof.ws.model.availability;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class AvailabilityRequest {

    private String originAirport;
    private String destinationAirport;
    private LocalDate from;
    private LocalDate to;
    private Paxes paxes;
}

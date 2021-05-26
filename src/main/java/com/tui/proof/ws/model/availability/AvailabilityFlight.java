package com.tui.proof.ws.model.availability;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents the availability of the flights
 */
@Data
@Accessors(chain = true)
public class AvailabilityFlight {

    private LocalDateTime expirationTime;
    private List<Flight> flights;

}
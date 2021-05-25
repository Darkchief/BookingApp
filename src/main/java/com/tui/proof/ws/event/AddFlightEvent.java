package com.tui.proof.ws.event;

import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class AddFlightEvent {

    private String email;
    private String flightNumber;
    private List<Flight> availableFlights;
    private Map<String, Reservation> reservationMap;
}

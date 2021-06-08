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

    private Long reservationCode;
    private Long flightNumber;
    private List<Flight> availableFlights;
    private Map<Long, Reservation> reservationMap;
}

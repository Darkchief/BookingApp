package com.tui.proof.ws.event;

import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class DeleteFlightEvent {

    private Long reservationCode;
    private Long flightNumber;
    private Map<Long, Reservation> reservationMap;
}

package com.tui.proof.ws.event;

import com.tui.proof.ws.model.availability.AvailabilityFlight;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class ConfirmReservationEvent {

    private Long reservationCode;
    private Map<Long, Reservation> reservationMap;
    private AvailabilityFlight availabilityFlight;
}

package com.tui.proof.ws.model.booking;

import com.tui.proof.ws.model.availability.Flight;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.TreeSet;

@Data
@Accessors(chain = true)
public class Reservation {

    private Holder holder;
    private ReservationStatus status;
    private Set<Flight> flights = new TreeSet<>();
}

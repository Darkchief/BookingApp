package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.DeleteFlightEvent;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.Reservation;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class DeleteFlightListenerTest {

    @Test
    void handleDeleteFlightEvent() {
        Long reservationCode = 123456789L;
        Long flightNumber = 123456L;
        DeleteFlightEvent event = new DeleteFlightEvent()
                .setReservationCode(reservationCode)
                .setFlightNumber(flightNumber)
                .setReservationMap(createReservationMap(reservationCode, flightNumber));

        DeleteFlightListener listener = new DeleteFlightListener();

        Flight flight = new Flight().setFlightNumber(flightNumber);
        assertTrue(event.getReservationMap().get(reservationCode).getFlights().contains(flight));

        listener.handleDeleteFlightEvent(event);

        assertFalse(event.getReservationMap().get(reservationCode).getFlights().contains(flight));
    }

    private Map<Long, Reservation> createReservationMap(Long reservationCode, Long flightNumber) {
        Set<Flight> flights = new TreeSet<>();
        flights.add(new Flight().setFlightNumber(125521L));
        flights.add(new Flight().setFlightNumber(flightNumber));

        Map<Long, Reservation> map = new HashMap<>();
        map.put(reservationCode, new Reservation().setFlights(flights));

        return map;
    }
}
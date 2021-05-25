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
        String testMail = "test.mail@gmail.com";
        Long flightNumber = 123456L;
        DeleteFlightEvent event = new DeleteFlightEvent()
                .setEmail(testMail)
                .setFlightNumber(flightNumber)
                .setReservationMap(createReservationMap(testMail, flightNumber));

        DeleteFlightListener listener = new DeleteFlightListener();

        Flight flight = new Flight().setFlightNumber(flightNumber);
        assertTrue(event.getReservationMap().get(testMail).getFlights().contains(flight));

        listener.handleDeleteFlightEvent(event);

        assertFalse(event.getReservationMap().get(testMail).getFlights().contains(flight));
    }

    private Map<String, Reservation> createReservationMap(String mail, Long flightNumber) {
        Set<Flight> flights = new TreeSet<>();
        flights.add(new Flight().setFlightNumber(125521L));
        flights.add(new Flight().setFlightNumber(flightNumber));

        Map<String, Reservation> map = new HashMap<>();
        map.put(mail, new Reservation().setFlights(flights));

        return map;
    }
}
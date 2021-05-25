package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.AddFlightEvent;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.Holder;
import com.tui.proof.ws.model.booking.Reservation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

class AddFlightListenerTest {

    @Test
    void handleAddFlightEventTest() {
        String validEmail = "mario.rossi@gmail.com";
        String notValidEmail = "a.b@c.d";

        AddFlightEvent event = new AddFlightEvent()
                .setEmail(notValidEmail)
                .setFlightNumber(123456L)
                .setReservationMap(createReservationMap())
                .setAvailableFlights(createAvailableFlightList());

        AddFlightListener listener = new AddFlightListener();

        // Call listener
        listener.handleAddFlightEvent(event);
        Reservation reservation = event.getReservationMap().get(validEmail);
        assertNotNull(reservation);
        assertFalse(reservation.getFlights().stream()
                .anyMatch(flight -> event.getFlightNumber().equals(flight.getFlightNumber())));

        event.setEmail(validEmail);
        // Call Listener
        listener.handleAddFlightEvent(event);
        assertTrue(reservation.getFlights().stream()
                .anyMatch(flight -> event.getFlightNumber().equals(flight.getFlightNumber())));

    }

    private Map<String, Reservation> createReservationMap() {
        Map<String, Reservation> map = new HashMap<>();
        map.put("mario.rossi@gmail.com", new Reservation()
                .setHolder(new Holder()
                        .setName("Mario")
                        .setLastName("Rossi")
                        .setAddress("Via Zurigo")
                        .setPostalCode("20147")
                        .setCountry("Italy")
                        .setEmail("mario.rossi@gmail.com")
                        .setTelephones(new ArrayList<>())));
        return map;
    }

    private List<Flight> createAvailableFlightList() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight()
                .setFlightNumber(123456L));

        return flights;
    }
}
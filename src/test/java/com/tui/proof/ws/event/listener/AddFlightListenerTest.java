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
        Long validReservationCode = 123456789L;
        Long notValidReservationCode = 87L;

        AddFlightEvent event = new AddFlightEvent()
                .setReservationCode(notValidReservationCode)
                .setFlightNumber(123456L)
                .setReservationMap(createReservationMap())
                .setAvailableFlights(List.of(new Flight().setFlightNumber(123456L)));

        AddFlightListener listener = new AddFlightListener();

        // Call listener
        listener.handleAddFlightEvent(event);
        Reservation reservation = event.getReservationMap().get(validReservationCode);
        assertNotNull(reservation);
        assertFalse(reservation.getFlights().stream()
                .anyMatch(flight -> event.getFlightNumber().equals(flight.getFlightNumber())));

        event.setReservationCode(validReservationCode);
        // Call Listener
        listener.handleAddFlightEvent(event);
        assertTrue(reservation.getFlights().stream()
                .anyMatch(flight -> event.getFlightNumber().equals(flight.getFlightNumber())));

    }

    private Map<Long, Reservation> createReservationMap() {
        Map<Long, Reservation> map = new HashMap<>();
        map.put(123456789L, new Reservation()
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
}
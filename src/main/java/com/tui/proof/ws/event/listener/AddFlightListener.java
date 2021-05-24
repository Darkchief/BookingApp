package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.AddFlightEvent;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class AddFlightListener {

    @Async
    @EventListener
    void handleAddNewFlightEvent(AddFlightEvent event) {
        log.info("Listen to the newReservationEvent");
        String email = event.getEmail();
        Map<String, Reservation> reservationMap = event.getReservationMap();
        Flight flightToAdd = event.getFlight();

        if (!reservationMap.containsKey(email)) {
            log.info("Email {} does not contains a reservation, unable to add the flight", email);
        } else {
            reservationMap.computeIfPresent(email, (k, v) -> v).getFlights().add(flightToAdd);
        }
    }
}

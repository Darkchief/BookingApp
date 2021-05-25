package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.AddFlightEvent;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class AddFlightListener {

    @Async
    @EventListener
    void handleAddFlightEvent(AddFlightEvent event) {
        log.info("Listen to the addFlightEvent");
        String email = event.getEmail();
        Map<String, Reservation> reservationMap = event.getReservationMap();

        if (!reservationMap.containsKey(email)) {
            log.info("Email {} does not contains a reservation, unable to add the flight", email);
        } else {
            Optional<Flight> flightToAdd = event.getAvailableFlights().stream()
                    .filter(flight -> event.getFlightNumber().equals(flight.getFlightNumber()))
                    .findFirst();

            if(flightToAdd.isPresent()) {
                reservationMap.computeIfPresent(email, (k, v) -> v).getFlights().add(flightToAdd.get());
            } else {
                log.info("There are no available flight with number {}", event.getFlightNumber());
            }
        }
    }
}

package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.AddFlightEvent;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.model.booking.ReservationStatus;
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
        Long reservationCode = event.getReservationCode();
        Map<Long, Reservation> reservationMap = event.getReservationMap();

        if (!reservationMap.containsKey(reservationCode)) {
            log.info("There are no reservations associated with the reservation code {}, unable to add the flight", reservationCode);
        } else {
            Optional<Flight> flightToAdd = event.getAvailableFlights().stream()
                    .filter(flight -> event.getFlightNumber().equals(flight.getFlightNumber()))
                    .findFirst();

            if(flightToAdd.isPresent()) {
                Reservation reservation = reservationMap.get(reservationCode);
                reservation.getFlights().add(flightToAdd.get());
                reservation.setStatus(ReservationStatus.IN_PROGRESS);
            } else {
                log.info("There are no available flights with number {}", event.getFlightNumber());
            }
        }
    }
}

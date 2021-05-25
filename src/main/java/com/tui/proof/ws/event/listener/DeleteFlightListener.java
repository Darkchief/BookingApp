package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.DeleteFlightEvent;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteFlightListener {

    @Async
    @EventListener
    void handleDeleteFlightEvent(DeleteFlightEvent event) {
        log.info("Listen to the deleteFlightEvent");
        String email = event.getEmail();
        Reservation reservation = event.getReservationMap().get(email);
        Long flightNumber = event.getFlightNumber();

        if (reservation == null) {
            log.info("There are no reservation for email {}, unable to remove the flight", email);
        } else {
            if (!reservation.getFlights().removeIf(flight -> flightNumber.equals(flight.getFlightNumber()))) {
                log.info("There are no flight in the reservation with number {}", flightNumber);
            }
        }
    }
}

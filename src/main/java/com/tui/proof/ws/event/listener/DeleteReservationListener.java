package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.DeleteReservationEvent;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class DeleteReservationListener {

    @Async
    @EventListener
    void handleDeleteReservationEvent(DeleteReservationEvent event) {
        log.info("Listen to the deleteReservationEvent");
        String email = event.getEmail();
        Map<String, Reservation> reservationMap = event.getReservationMap();

        if (reservationMap.containsKey(email)) {
            event.getReservationMap().remove(email);
        } else {
            log.info("There are no reservations associated with the email {}", email);
        }
    }
}

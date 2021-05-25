package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.ConfirmReservationEvent;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ConfirmReservationListener {

    @Async
    @EventListener
    void handleConfirmFlightEvent(ConfirmReservationEvent event) {
        log.info("Listen to the confirmReservationEvent");
        String email = event.getEmail();
        Map<String, Reservation> reservationMap = event.getReservationMap();
        //TODO: confirm if all is valid
    }
}

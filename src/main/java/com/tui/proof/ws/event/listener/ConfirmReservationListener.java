package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.ConfirmReservationEvent;
import com.tui.proof.ws.model.availability.AvailabilityFlight;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.model.booking.ReservationStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
public class ConfirmReservationListener {

    @Async
    @EventListener
    void handleConfirmFlightEvent(ConfirmReservationEvent event) {
        log.info("Listen to the confirmReservationEvent");
        Long reservationCode = event.getReservationCode();
        Map<Long, Reservation> reservationMap = event.getReservationMap();
        AvailabilityFlight availabilityFlight = event.getAvailabilityFlight();

        if (reservationMap.containsKey(reservationCode)
                && availabilityFlight.getExpirationTime() != null
                && LocalDateTime.now().isBefore(availabilityFlight.getExpirationTime())) {
            reservationMap.get(reservationCode).setStatus(ReservationStatus.CONFIRMED);
            log.info("All data are correct, reservation confirmed");
        } else {
            log.info("Some data are incorrect, reservation cannot be confirmed");
        }
    }
}

package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.CreateReservationEvent;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class CreateReservationListener {

    @Async
    @EventListener
    void handleCreateReservationEvent(CreateReservationEvent event) {
        log.info("Listen to the createReservationEvent");
        Map<String, Reservation> reservationMap = event.getReservationMap();
        HolderRequest holderData = event.getHolderData();
        String email = holderData.getHolder().getEmail();
        if (reservationMap.containsKey(email)) {
            log.info("Email {} already has a reservation, creating a new one", email);
        }
        reservationMap.put(email, new Reservation()
                .setHolder(holderData.getHolder()));
    }
}
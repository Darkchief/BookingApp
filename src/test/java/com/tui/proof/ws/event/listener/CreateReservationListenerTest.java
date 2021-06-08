package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.CreateReservationEvent;
import com.tui.proof.ws.model.booking.Holder;
import com.tui.proof.ws.model.booking.HolderRequest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateReservationListenerTest {

    @Test
    void handleCreateReservationEvent() {
        Long reservationCode = 123456789L;
        CreateReservationListener listener = new CreateReservationListener();

        CreateReservationEvent event = new CreateReservationEvent()
                .setHolderData(new HolderRequest()
                        .setHolder(new Holder().setEmail("mario.rossi@gmail.com")))
                .setReservationMap(new HashMap<>())
                .setReservationCode(reservationCode);

        listener.handleCreateReservationEvent(event);
        assertTrue(event.getReservationMap().containsKey(reservationCode));
        assertEquals(event.getReservationMap().size(), 1);
    }
}
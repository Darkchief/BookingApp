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
        String testMail = "test.mail@gmail.com";
        CreateReservationListener listener = new CreateReservationListener();

        CreateReservationEvent event = new CreateReservationEvent()
                .setHolderData(new HolderRequest()
                        .setHolder(new Holder().setEmail(testMail)))
                .setReservationMap(new HashMap<>());

        listener.handleCreateReservationEvent(event);
        assertTrue(event.getReservationMap().containsKey(testMail));
        assertEquals(event.getReservationMap().size(), 1);
    }
}
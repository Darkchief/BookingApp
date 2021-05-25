package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.event.DeleteReservationEvent;
import com.tui.proof.ws.model.booking.Holder;
import com.tui.proof.ws.model.booking.Reservation;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteReservationListenerTest {

    @Test
    void handleDeleteReservationEvent() {
        String testMail = "test.mail@gmail.com";
        DeleteReservationListener listener = new DeleteReservationListener();

        DeleteReservationEvent event = new DeleteReservationEvent()
                .setEmail(testMail)
                .setReservationMap(createReservationMap(testMail));

        assertTrue(event.getReservationMap().containsKey(testMail));
        listener.handleDeleteReservationEvent(event);
        assertFalse(event.getReservationMap().containsKey(testMail));
    }

    private Map<String, Reservation> createReservationMap(String mail) {
        Map<String, Reservation> map = new HashMap<>();
        map.put(mail, new Reservation());
        map.put("mario.rossi@gmail.com", new Reservation().setHolder(new Holder().setEmail("mario.rossi@gmail.com")));
        return map;
    }
}
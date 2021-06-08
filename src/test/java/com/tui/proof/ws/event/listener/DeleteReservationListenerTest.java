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
        Long reservationCode = 123456789L;
        DeleteReservationListener listener = new DeleteReservationListener();

        DeleteReservationEvent event = new DeleteReservationEvent()
                .setReservationCode(reservationCode)
                .setReservationMap(createReservationMap(reservationCode));

        assertTrue(event.getReservationMap().containsKey(reservationCode));
        listener.handleDeleteReservationEvent(event);
        assertFalse(event.getReservationMap().containsKey(reservationCode));
    }

    private Map<Long, Reservation> createReservationMap(Long reservationCode) {
        Map<Long, Reservation> map = new HashMap<>();
        map.put(reservationCode, new Reservation());
        map.put(87L, new Reservation().setHolder(new Holder().setEmail("mario.rossi@gmail.com")));
        return map;
    }
}
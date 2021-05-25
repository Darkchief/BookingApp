package com.tui.proof.ws.event.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.ws.event.CheckAvailabilityEvent;
import com.tui.proof.ws.model.availability.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckAvailabilityListenerTest {

    @Test
    void handleAvailabilityEvent() {
        List<Flight> flights = new ArrayList<>();
        CheckAvailabilityListener listener = new CheckAvailabilityListener();
        listener.setObjectMapper(new ObjectMapper());

        //Call listener
        listener.handleAvailabilityEvent(new CheckAvailabilityEvent().setFlights(flights));

        assertTrue(!CollectionUtils.isEmpty(flights));
        assertThat(flights, hasSize(1));
    }
}
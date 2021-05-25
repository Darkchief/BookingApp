package com.tui.proof.ws.event.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.ws.event.CheckAvailabilityEvent;
import com.tui.proof.ws.model.availability.Flight;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Component
public class CheckAvailabilityListener {

    @Autowired
    ObjectMapper objectMapper;

    @Async
    @EventListener
    void handleAvailabilityEvent(CheckAvailabilityEvent event) {
        log.info("Listen to the availabilityEvent");
        List<Flight> flights = new ArrayList<>();
        try {
            flights = objectMapper.readValue(this.getClass().getResourceAsStream("/flights.json")
                    , new TypeReference<List<Flight>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug("Available flights retrieved: {}", flights);
        event.getFlights().addAll(flights);
    }

}

package com.tui.proof.ws.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.ws.event.listener.CheckAvailabilityEvent;
import com.tui.proof.ws.model.AvailabilityRequest;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String test() {
        log.info("Start test method");
        return "Test Method";
    }

    @Override
    public List<Flight> checkAvailability(AvailabilityRequest request) {
        List<Flight> flights = new ArrayList<>();
        CheckAvailabilityEvent event = new CheckAvailabilityEvent().setFlights(flights);
        publisher.publishEvent(event);
        return flights;
    }

}

package com.tui.proof.ws.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.proof.ws.model.AvailabilityRequest;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Monetary;
import com.tui.proof.ws.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    ObjectMapper objectMapper;

    @Value("classpath:flights.json")
    private Resource flights;

    @Override
    public String test() {
        log.info("Start test method");
        return "Test Method";
    }

    @Override
    public List<Flight> checkAvailability(AvailabilityRequest request) {
        Flight flight;
        try {
            flight = objectMapper.readValue(flights.getFile(), Flight.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm");
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight()
                .setCompany("company")
                .setFlightNumber(Long.valueOf("123456"))
                .setDate(LocalDate.now())
                .setHour(LocalTime.now().format(dateTimeFormatter))
                .setPrice(new Monetary().setAmount(BigDecimal.valueOf(123456.65)).setCurrency("EUR")));

        return flights;
    }

}

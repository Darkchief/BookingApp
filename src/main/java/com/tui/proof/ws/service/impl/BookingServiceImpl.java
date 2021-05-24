package com.tui.proof.ws.service.impl;

import com.tui.proof.ws.event.AddFlightEvent;
import com.tui.proof.ws.event.AddNewReservationEvent;
import com.tui.proof.ws.event.CheckAvailabilityEvent;
import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private ApplicationEventPublisher publisher;

    private Map<String, Reservation> reservationMap = new HashMap();

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
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return flights;
    }

    @Override
    public void createNewReservation(HolderRequest request) {
        AddNewReservationEvent event = new AddNewReservationEvent()
                .setReservationMap(reservationMap)
                .setHolderData(request);
        publisher.publishEvent(event);
    }

    @Override
    public void addFlight(FlightRequest request) {
        AddFlightEvent event = new AddFlightEvent()
                .setEmail(request.getEmail())
                .setReservationMap(reservationMap)
                .setFlight(request.getFlight());
        publisher.publishEvent(event);
    }

    @Override
    public Reservation retrieveReservationDetails(String email) {
        return reservationMap.get(email);
    }

}

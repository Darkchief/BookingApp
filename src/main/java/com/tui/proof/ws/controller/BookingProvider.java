package com.tui.proof.ws.controller;

import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Controller interface, here you will find the exposed API
 */
public interface BookingProvider {

    ResponseEntity<String> test();

    ResponseEntity<List<Flight>> checkAvailability(AvailabilityRequest request);

    ResponseEntity<Void> createNewReservation(HolderRequest request);

    ResponseEntity<Void> addFlight(FlightRequest request);

    ResponseEntity<Reservation> reservationDetails(String email);
}

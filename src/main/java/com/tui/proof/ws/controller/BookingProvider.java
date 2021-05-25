package com.tui.proof.ws.controller;

import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller interface, here you will find the exposed API
 */
public interface BookingProvider {

    ResponseEntity<List<Flight>> checkAvailability(HttpServletRequest httpRequest, AvailabilityRequest request);

    ResponseEntity<Void> createReservation(HttpServletRequest httpRequest, HolderRequest request);

    ResponseEntity<Void> addFlight(HttpServletRequest httpRequest, FlightRequest request);

    ResponseEntity<Void> deleteFlight(HttpServletRequest httpRequest, FlightRequest request);

    ResponseEntity<Reservation> reservationDetails(HttpServletRequest httpRequest, String email);

    ResponseEntity<Void> deleteReservation(HttpServletRequest httpRequest, String email);

    ResponseEntity<Void> confirmReservation(HttpServletRequest httpRequest, String email);

}
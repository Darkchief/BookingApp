package com.tui.proof.ws.controller;

import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.model.booking.ReservationResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Interface for the controller, here you will find the exposed api
 */
public interface BookingProvider {

    ResponseEntity<List<Flight>> checkAvailability(HttpServletRequest httpRequest, AvailabilityRequest request);

    ResponseEntity<ReservationResponse> createReservation(HttpServletRequest httpRequest, HolderRequest request);

    ResponseEntity<Void> addFlight(HttpServletRequest httpRequest, FlightRequest request);

    ResponseEntity<Void> deleteFlight(HttpServletRequest httpRequest, FlightRequest request);

    ResponseEntity<Reservation> reservationDetails(HttpServletRequest httpRequest, String reservationCode);

    ResponseEntity<Void> deleteReservation(HttpServletRequest httpRequest, String reservationCode);

    ResponseEntity<Void> confirmReservation(HttpServletRequest httpRequest, String reservationCode);

}
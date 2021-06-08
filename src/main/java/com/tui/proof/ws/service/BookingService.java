package com.tui.proof.ws.service;

import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.model.booking.ReservationResponse;

import java.util.List;

/**
 * Interface for the service, here you will find the core methods
 */
public interface BookingService {

    boolean isUserLogged(String username, String password);

    List<Flight> checkAvailability(AvailabilityRequest request);

    ReservationResponse createReservation(HolderRequest request);

    void addFlight(FlightRequest request);

    void deleteFlight(FlightRequest request);

    Reservation retrieveReservationDetails(String reservationCode);

    void deleteReservation(String reservationCode);

    void confirmReservation(String reservationCode);
}

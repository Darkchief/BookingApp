package com.tui.proof.ws.service;

import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;

import java.util.List;

public interface BookingService {

    String test();

    List<Flight> checkAvailability(AvailabilityRequest request);

    void createNewReservation(HolderRequest request);

    void addFlight(FlightRequest request);

    Reservation retrieveReservationDetails(String email);
}

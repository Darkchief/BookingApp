package com.tui.proof.ws.service;

import com.tui.proof.ws.model.AvailabilityRequest;
import com.tui.proof.ws.model.Flight;

import java.util.List;

public interface BookingService {

    String test();

    List<Flight> checkAvailability(AvailabilityRequest request);


}

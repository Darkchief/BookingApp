package com.tui.proof.ws.controller;

import com.tui.proof.ws.model.AvailabilityRequest;
import com.tui.proof.ws.model.Flight;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Controller interface, here you will find the exposed API
 */
public interface BookingProvider {

    ResponseEntity<String> test();

    ResponseEntity<List<Flight>> checkAvailability(AvailabilityRequest request);
}

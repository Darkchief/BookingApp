package com.tui.proof.ws.controller.impl;

import com.tui.proof.ws.controller.BookingProvider;
import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.service.BookingService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Log4j2
@RestController
@RequestMapping("/book")
@Accessors(chain = true)
public class BookingController implements BookingProvider {

    @Autowired
    private BookingService bookingService;

    @Override
    @PostMapping(value = "/checkAvailability")
    public ResponseEntity<List<Flight>> checkAvailability(@RequestBody AvailabilityRequest request) {
        log.info("Availability Request: {}", request);

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.checkAvailability(request));

    }

    @Override
    @PutMapping(value = "/createReservation")
    public ResponseEntity<Void> createReservation(@RequestBody HolderRequest request) {
        log.info("Start createNewReservation");
        bookingService.createNewReservation(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @PutMapping(value = "/addFlight")
    public ResponseEntity<Void> addFlight(@RequestBody FlightRequest request) {
        log.info("Start addFlight");
        bookingService.addFlight(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @DeleteMapping(value = "/deleteFlight")
    public ResponseEntity<Void> deleteFlight(@RequestBody FlightRequest request) {
        bookingService.deleteFlight(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @GetMapping(value = "/details/{email}")
    public ResponseEntity<Reservation> reservationDetails(@PathVariable("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.retrieveReservationDetails(email));
    }

    @Override
    @DeleteMapping(value = "/deleteReservation/{email}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("email") String email) {
        bookingService.deleteReservation(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @PostMapping(value = "/confirmReservation/{email}")
    public ResponseEntity<Void> confirmReservation(@PathVariable("email") String email) {
        bookingService.confirmReservation(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}


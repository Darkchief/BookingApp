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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public ResponseEntity<List<Flight>> checkAvailability(HttpServletRequest httpRequest, @RequestBody AvailabilityRequest request) {
        log.info("Availability Request: {}", request);
        ResponseEntity<List<Flight>> response;
        if (isUserLogged(httpRequest)) {
            List<Flight> flights = bookingService.checkAvailability(request);
            response = ResponseEntity.status(HttpStatus.OK).body(flights);
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());
        }
        return response;
    }

    @Override
    @PutMapping(value = "/createReservation")
    public ResponseEntity<Void> createReservation(HttpServletRequest httpRequest, @RequestBody HolderRequest request) {
        log.info("Start createNewReservation");
        ResponseEntity<Void> response;
        if (isUserLogged(httpRequest)) {
            bookingService.createNewReservation(request);
            response = ResponseEntity.status(HttpStatus.OK).build();
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return response;
    }

    @Override
    @PutMapping(value = "/addFlight")
    public ResponseEntity<Void> addFlight(HttpServletRequest httpRequest, @RequestBody FlightRequest request) {
        log.info("Start addFlight");
        ResponseEntity<Void> response;
        if (isUserLogged(httpRequest)) {
            bookingService.addFlight(request);
            response = ResponseEntity.status(HttpStatus.OK).build();
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return response;
    }

    @Override
    @DeleteMapping(value = "/deleteFlight")
    public ResponseEntity<Void> deleteFlight(HttpServletRequest httpRequest, @RequestBody FlightRequest request) {
        log.info("Start addFlight");
        ResponseEntity<Void> response;
        if (isUserLogged(httpRequest)) {
            bookingService.deleteFlight(request);
            response = ResponseEntity.status(HttpStatus.OK).build();
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return response;
    }

    @Override
    @GetMapping(value = "/details/{email}")
    public ResponseEntity<Reservation> reservationDetails(HttpServletRequest httpRequest, @PathVariable("email") String email) {
        log.info("Start addFlight");
        ResponseEntity<Reservation> response;
        if (isUserLogged(httpRequest)) {
            Reservation reservation = bookingService.retrieveReservationDetails(email);
            response = ResponseEntity.status(HttpStatus.OK).body(reservation);
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return response;
    }

    @Override
    @DeleteMapping(value = "/deleteReservation/{email}")
    public ResponseEntity<Void> deleteReservation(HttpServletRequest httpRequest, @PathVariable("email") String email) {
        log.info("Start addFlight");
        ResponseEntity<Void> response;
        if (isUserLogged(httpRequest)) {
            bookingService.deleteReservation(email);
            response = ResponseEntity.status(HttpStatus.OK).build();
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return response;
    }

    @Override
    @PostMapping(value = "/confirmReservation/{email}")
    public ResponseEntity<Void> confirmReservation(HttpServletRequest httpRequest, @PathVariable("email") String email) {
        log.info("Start addFlight");
        ResponseEntity<Void> response;
        if (isUserLogged(httpRequest)) {
            bookingService.confirmReservation(email);
            response = ResponseEntity.status(HttpStatus.OK).build();
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return response;
    }

    private boolean isUserLogged(HttpServletRequest httpRequest) {
        return bookingService.isUserLogged(httpRequest.getHeader("username"), httpRequest.getHeader("password"));
    }
}


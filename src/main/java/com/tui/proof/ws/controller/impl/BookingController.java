package com.tui.proof.ws.controller.impl;

import com.tui.proof.ws.controller.BookingProvider;
import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.model.booking.ReservationResponse;
import com.tui.proof.ws.service.BookingService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This is the Controller, here you will find all the implementation of the exposed API
 */
@Data
@Log4j2
@RestController
@RequestMapping("/book")
@Accessors(chain = true)
public class BookingController implements BookingProvider {

    @Autowired
    private BookingService bookingService;

    /**
     * Api to retrieve available flights
     *
     * @param httpRequest Request to verify that the user is authorized to use the API
     * @param request     Flight details
     * @return A list of flights
     */
    @Override
    @PostMapping(value = "/checkAvailability")
    public ResponseEntity<List<Flight>> checkAvailability(HttpServletRequest httpRequest,
                                                          @RequestBody AvailabilityRequest request) {
        log.info("Start checkAvailability method");
        ResponseEntity<List<Flight>> response = null;
        if (isUserLogged(httpRequest)) {
            List<Flight> flights = bookingService.checkAvailability(request);
            response = ResponseEntity.status(HttpStatus.OK).body(flights);
        }
        return response;
    }

    /**
     * Create a new booking associated with a reservationCode
     *
     * @param httpRequest Request to verify that the user is authorized to use the API
     * @param request     that contains the details of the customer who wants to book
     */
    @Override
    @PutMapping(value = "/createReservation")
    public ResponseEntity<ReservationResponse> createReservation(HttpServletRequest httpRequest, @RequestBody HolderRequest request) {
        log.info("Start createReservation method");
        ResponseEntity<ReservationResponse> response = null;
        if (isUserLogged(httpRequest)) {
            ReservationResponse reservationResponse = bookingService.createReservation(request);
            response = ResponseEntity.status(HttpStatus.OK).body(reservationResponse);
        }
        return response;
    }

    /**
     * Add a flight to a reservation
     *
     * @param httpRequest Request to verify that the user is authorized to use the API
     * @param request     which contains the reservationCode of the booking and the flight to add to the booking
     */
    @Override
    @PutMapping(value = "/addFlight")
    public ResponseEntity<Void> addFlight(HttpServletRequest httpRequest, @RequestBody FlightRequest request) {
        log.info("Start addFlight method");
        ResponseEntity<Void> response = null;
        if (isUserLogged(httpRequest)) {
            bookingService.addFlight(request);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        return response;
    }

    /**
     * Remove a flight from a reservation
     *
     * @param httpRequest Request to verify that the user is authorized to use the API
     * @param request     which contains the reservationCode of the booking and the flight to remove from the booking
     */
    @Override
    @DeleteMapping(value = "/deleteFlight")
    public ResponseEntity<Void> deleteFlight(HttpServletRequest httpRequest, @RequestBody FlightRequest request) {
        log.info("Start deleteFlight method");
        ResponseEntity<Void> response = null;
        if (isUserLogged(httpRequest)) {
            bookingService.deleteFlight(request);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        return response;
    }

    /**
     * API that returns the booking details associated with the reservationCode
     *
     * @param httpRequest Request to verify that the user is authorized to use the API
     * @param reservationCode       the reservation identifier
     * @return The reservation details
     */
    @Override
    @GetMapping(value = "/details/{reservationCode}")
    public ResponseEntity<Reservation> reservationDetails(HttpServletRequest httpRequest, @PathVariable("reservationCode") String reservationCode) {
        log.info("Start details method");
        ResponseEntity<Reservation> response = null;
        if (isUserLogged(httpRequest)) {
            Reservation reservation = bookingService.retrieveReservationDetails(reservationCode);
            response = ResponseEntity.status(HttpStatus.OK).body(reservation);
        }
        return response;
    }

    /**
     * Delete the reservation associated with the reservationCode
     *
     * @param httpRequest Request to verify that the user is authorized to use the API
     * @param reservationCode       the reservation identifier
     */
    @Override
    @DeleteMapping(value = "/deleteReservation/{reservationCode}")
    public ResponseEntity<Void> deleteReservation(HttpServletRequest httpRequest, @PathVariable("reservationCode") String reservationCode) {
        log.info("Start deleteReservation method");
        ResponseEntity<Void> response = null;
        if (isUserLogged(httpRequest)) {
            bookingService.deleteReservation(reservationCode);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        return response;
    }

    /**
     * Confirm the reservation associated with the reservationCode
     *
     * @param httpRequest Request to verify that the user is authorized to use the API
     * @param reservationCode       the reservation identifier
     */
    @Override
    @PostMapping(value = "/confirmReservation/{reservationCode}")
    public ResponseEntity<Void> confirmReservation(HttpServletRequest httpRequest, @PathVariable("reservationCode") String reservationCode) {
        log.info("Start confirmReservation method");
        ResponseEntity<Void> response = null;
        if (isUserLogged(httpRequest)) {
            bookingService.confirmReservation(reservationCode);
            response = ResponseEntity.status(HttpStatus.OK).build();
        }
        return response;
    }

    /**
     * This method checks whether the user is authorized to use the APIs
     *
     * @param httpRequest which contains username and password
     * @return true if the user is authorized, throw UnauthorizedException if not
     */
    private boolean isUserLogged(HttpServletRequest httpRequest) {
        return bookingService.isUserLogged(httpRequest.getHeader("username"), httpRequest.getHeader("password"));
    }
}


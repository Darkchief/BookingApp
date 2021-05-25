package com.tui.proof.ws.service.impl;

import com.tui.proof.ws.exception.AvailabilityFlightExpiredException;
import com.tui.proof.ws.exception.ReservationNotExistException;
import com.tui.proof.ws.model.availability.AvailabilityFlight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.service.BookingService;
import com.tui.proof.ws.validation.RequestValidatorFactory;
import com.tui.proof.ws.validation.impl.FlightRequestValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.Assert.assertTrue;

class BookingServiceImplTest {

    @Test
    void addFlightExpiration() {
        boolean thrown = false;
        BookingService bookingService = new BookingServiceImpl().setAvailabilityFlight(new AvailabilityFlight()
                .setExpirationTime(LocalDateTime.now().minusMinutes(15L)))
                .setValidatorFactory(new RequestValidatorFactory()
                        .setValidators(Map.of(FlightRequestValidator.class, new FlightRequestValidator())));

        try {
            bookingService.addFlight(new FlightRequest()
                    .setEmail("mario.rossi@gmail.com")
                    .setFlightNumber(123456L));
        } catch (AvailabilityFlightExpiredException ex) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    void confirmReservationExpiration() {
        boolean thrown = false;
        BookingService bookingService = new BookingServiceImpl().setAvailabilityFlight(new AvailabilityFlight()
                .setExpirationTime(LocalDateTime.now().minusMinutes(14L)));

        try {
            bookingService.confirmReservation("mario.rossi@gmail.com");
        } catch (ReservationNotExistException ex) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}
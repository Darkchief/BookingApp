package com.tui.proof.ws.service.impl;

import com.tui.proof.ws.event.*;
import com.tui.proof.ws.exception.*;
import com.tui.proof.ws.model.availability.AvailabilityFlight;
import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.service.BookingService;
import com.tui.proof.ws.validation.RequestValidatorFactory;
import com.tui.proof.ws.validation.impl.AvailabilityRequestValidator;
import com.tui.proof.ws.validation.impl.FlightRequestValidator;
import com.tui.proof.ws.validation.impl.HolderRequestValidator;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
@Service
@Accessors(chain = true)
public class BookingServiceImpl implements BookingService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RequestValidatorFactory validatorFactory;

    private Map<String, Reservation> reservationMap = new HashMap();

    private AvailabilityFlight availabilityFlight = new AvailabilityFlight();

    @Override
    public List<Flight> checkAvailability(AvailabilityRequest request) {
        String message = validatorFactory.getValidators().get(AvailabilityRequestValidator.class).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new AvailabilityRequestNotValidException(message);
        }

        List<Flight> flights = new ArrayList<>();
        availabilityFlight.setExpirationTime(LocalDateTime.now().plusMinutes(15))
                .setFlights(flights);

        CheckAvailabilityEvent event = new CheckAvailabilityEvent().setFlights(flights);
        publisher.publishEvent(event);
        return availabilityFlight.getFlights();
    }

    @Override
    public void createNewReservation(HolderRequest request) {
        String message = validatorFactory.getValidators().get(HolderRequestValidator.class).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new HolderRequestNotValidException(message);
        }

        CreateReservationEvent event = new CreateReservationEvent()
                .setReservationMap(reservationMap)
                .setHolderData(request);
        publisher.publishEvent(event);
    }

    @Override
    public void addFlight(FlightRequest request) {
        String message = validatorFactory.getValidators().get(FlightRequestValidator.class).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new FlightRequestNotValidException(message);
        }

        availabilityFlightExpiration();

        AddFlightEvent event = new AddFlightEvent()
                .setEmail(request.getEmail())
                .setAvailableFlights(availabilityFlight.getFlights())
                .setReservationMap(reservationMap)
                .setFlightNumber(request.getFlightNumber());
        publisher.publishEvent(event);
    }

    @Override
    public void deleteFlight(FlightRequest request) {
        String message = validatorFactory.getValidators().get(FlightRequestValidator.class).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new FlightRequestNotValidException(message);
        }

        DeleteFlightEvent event = new DeleteFlightEvent()
                .setEmail(request.getEmail())
                .setReservationMap(reservationMap)
                .setFlightNumber(request.getFlightNumber());
        publisher.publishEvent(event);
    }

    @Override
    public Reservation retrieveReservationDetails(String email) {
        if (StringUtils.isBlank(email)) {
            throw new EmailNotValidException("Email must not be blank");
        }

        if (!reservationMap.containsKey(email)) {
            throw new ReservationNotExistException(email);
        }

        return reservationMap.get(email);
    }

    @Override
    public void deleteReservation(String email) {
        if (StringUtils.isBlank(email)) {
            throw new EmailNotValidException("Email must not be blank");
        }

        DeleteReservationEvent event = new DeleteReservationEvent()
                .setEmail(email)
                .setReservationMap(reservationMap);
        publisher.publishEvent(event);
    }

    @Override
    public void confirmReservation(String email) {
        if (StringUtils.isBlank(email)) {
            throw new EmailNotValidException("Email must not be blank");
        }

        availabilityFlightExpiration();

        if (!reservationMap.containsKey(email)) {
            throw new ReservationNotExistException(email);
        }

        ConfirmReservationEvent event = new ConfirmReservationEvent()
                .setEmail(email)
                .setReservationMap(reservationMap)
                .setAvailabilityFlight(availabilityFlight);
        publisher.publishEvent(event);
    }

    private void availabilityFlightExpiration() {
        if (availabilityFlight.getExpirationTime() == null
                || LocalDateTime.now().isAfter(availabilityFlight.getExpirationTime())) {
            throw new AvailabilityFlightExpiredException("The availability information are expired");
        }
    }
}
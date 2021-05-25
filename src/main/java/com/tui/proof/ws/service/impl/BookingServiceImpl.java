package com.tui.proof.ws.service.impl;

import com.tui.proof.ws.event.*;
import com.tui.proof.ws.exception.AvailabilityRequestNotValidException;
import com.tui.proof.ws.exception.HolderRequestNotValidException;
import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.service.BookingService;
import com.tui.proof.ws.validation.RequestValidatorFactory;
import com.tui.proof.ws.validation.impl.AvailabilityRequestValidator;
import com.tui.proof.ws.validation.impl.HolderRequestValidator;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    private List<Flight> flights;

    @Override
    public List<Flight> checkAvailability(AvailabilityRequest request) {
        String message = validatorFactory.getValidators().get(AvailabilityRequestValidator.class).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new AvailabilityRequestNotValidException(message);
        }

        if (CollectionUtils.isEmpty(flights)) {
            flights = new ArrayList<>();
        }
        CheckAvailabilityEvent event = new CheckAvailabilityEvent().setFlights(flights);
        publisher.publishEvent(event);
        return flights;
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
        //TODO: Validation
        AddFlightEvent event = new AddFlightEvent()
                .setEmail(request.getEmail())
                .setAvailableFlights(flights)
                .setReservationMap(reservationMap)
                .setFlightNumber(request.getFlightNumber());
        publisher.publishEvent(event);
    }

    @Override
    public void deleteFlight(FlightRequest request) {
        //TODO: Validation
        DeleteFlightEvent event = new DeleteFlightEvent()
                .setEmail(request.getEmail())
                .setReservationMap(reservationMap)
                .setFlightNumber(request.getFlightNumber());
        publisher.publishEvent(event);
    }

    @Override
    public Reservation retrieveReservationDetails(String email) {
        //TODO: Validation
        return reservationMap.get(email);
    }

    @Override
    public void deleteReservation(String email) {
        //TODO: Validation
        DeleteReservationEvent event = new DeleteReservationEvent()
                .setEmail(email)
                .setReservationMap(reservationMap);
        publisher.publishEvent(event);
    }

    @Override
    public void confirmReservation(String email) {
        //TODO: Validation
        ConfirmReservationEvent event = new ConfirmReservationEvent()
                .setEmail(email)
                .setReservationMap(reservationMap);
        publisher.publishEvent(event);
    }
}
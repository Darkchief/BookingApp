package com.tui.proof.ws.service.impl;

import com.tui.proof.ws.event.*;
import com.tui.proof.ws.exception.*;
import com.tui.proof.ws.model.availability.AvailabilityFlight;
import com.tui.proof.ws.model.availability.AvailabilityRequest;
import com.tui.proof.ws.model.availability.Flight;
import com.tui.proof.ws.model.booking.FlightRequest;
import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import com.tui.proof.ws.model.booking.ReservationResponse;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Here you will find the core methods implementation
 */
@Data
@Slf4j
@Service
@Accessors(chain = true)
public class BookingServiceImpl implements BookingService {

    @Value("${user.username}")
    private String username;

    @Value("${user.password}")
    private String password;


    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RequestValidatorFactory validatorFactory;

    private Map<Long, Reservation> reservationMap = new HashMap<>();

    private Long lastReservationCode = 40307L;

    private AvailabilityFlight availabilityFlight = new AvailabilityFlight();

    /**
     * This method verifies that the user is authorized to use the APIs
     *
     * @param loginUsername
     * @param loginPassword
     * @return true if authorized, throw UnauthorizedException if not
     */
    @Override
    public boolean isUserLogged(String loginUsername, String loginPassword) {
        if (username.equals(loginUsername) && password.equals(loginPassword)) {
            return true;
        }
        throw new UnauthorizedException("Invalid Username or Password");
    }

    @Override
    public List<Flight> checkAvailability(AvailabilityRequest request) {
        String message = ((AvailabilityRequestValidator) validatorFactory
                .getValidators().get(AvailabilityRequestValidator.class)).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new AvailabilityRequestNotValidException(message);
        }

        List<Flight> flights = new ArrayList<>();
        availabilityFlight.setExpirationTime(LocalDateTime.now().plusMinutes(15))
                .setFlights(flights);

        CheckAvailabilityEvent event = new CheckAvailabilityEvent().setFlights(flights);
        publisher.publishEvent(event);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return availabilityFlight.getFlights();
    }

    @Override
    public ReservationResponse createReservation(HolderRequest request) {
        String message = ((HolderRequestValidator) validatorFactory
                .getValidators().get(HolderRequestValidator.class)).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new HolderRequestNotValidException(message);
        }

        CreateReservationEvent event = new CreateReservationEvent()
                .setReservationMap(reservationMap)
                .setHolderData(request)
                .setReservationCode(++lastReservationCode);
        publisher.publishEvent(event);

        return new ReservationResponse().setReservationCode(lastReservationCode);
    }

    @Override
    public void addFlight(FlightRequest request) {
        String message = ((FlightRequestValidator) validatorFactory
                .getValidators().get(FlightRequestValidator.class)).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new FlightRequestNotValidException(message);
        }

        availabilityFlightExpiration();

        AddFlightEvent event = new AddFlightEvent()
                .setReservationCode(Long.parseLong(request.getReservationCode()))
                .setAvailableFlights(availabilityFlight.getFlights())
                .setReservationMap(reservationMap)
                .setFlightNumber(request.getFlightNumber());
        publisher.publishEvent(event);
    }

    @Override
    public void deleteFlight(FlightRequest request) {
        String message = ((FlightRequestValidator) validatorFactory
                .getValidators().get(FlightRequestValidator.class)).validate(request);
        if (StringUtils.isNotBlank(message)) {
            throw new FlightRequestNotValidException(message);
        }

        DeleteFlightEvent event = new DeleteFlightEvent()
                .setReservationCode(Long.parseLong(request.getReservationCode()))
                .setReservationMap(reservationMap)
                .setFlightNumber(request.getFlightNumber());
        publisher.publishEvent(event);
    }

    @Override
    public Reservation retrieveReservationDetails(String reservationCode) {
        if (StringUtils.isBlank(reservationCode)) {
            throw new ReservationCodeNotValidException("Reservation code must not be blank");
        }

        Long code;
        try {
            code = Long.parseLong(reservationCode);
        }catch (NumberFormatException ex) {
            throw new ReservationCodeFormatException("Reservation code must be a numeric value");
        }

        if (!reservationMap.containsKey(code)) {
            throw new ReservationNotExistException(reservationCode);
        }

        return reservationMap.get(code);
    }

    @Override
    public void deleteReservation(String reservationCode) {
        if (StringUtils.isBlank(reservationCode)) {
            throw new ReservationCodeNotValidException("Email must not be blank");
        }
        Long code;
        try {
            code = Long.parseLong(reservationCode);
        }catch (NumberFormatException ex) {
            throw new ReservationCodeFormatException("Reservation code must be a numeric value");
        }

        DeleteReservationEvent event = new DeleteReservationEvent()
                .setReservationCode(code)
                .setReservationMap(reservationMap);
        publisher.publishEvent(event);
    }

    @Override
    public void confirmReservation(String reservationCode) {
        if (StringUtils.isBlank(reservationCode)) {
            throw new ReservationCodeNotValidException("Reservation code must not be blank");
        }

        Long code;
        try {
            code = Long.parseLong(reservationCode);
        }catch (NumberFormatException ex) {
            throw new ReservationCodeFormatException("Reservation code must be a numeric value");
        }

        if (!reservationMap.containsKey(code)) {
            throw new ReservationNotExistException(reservationCode);
        }

        availabilityFlightExpiration();

        ConfirmReservationEvent event = new ConfirmReservationEvent()
                .setReservationCode(code)
                .setReservationMap(reservationMap)
                .setAvailabilityFlight(availabilityFlight);
        publisher.publishEvent(event);
    }

    /**
     * This method checks if the information is still available
     * 15 minutes after the last call to the checkAvailability API, the information expires
     */
    private void availabilityFlightExpiration() {
        if (availabilityFlight.getExpirationTime() == null
                || LocalDateTime.now().isAfter(availabilityFlight.getExpirationTime())) {
            throw new AvailabilityFlightExpiredException("The availability information are expired");
        }
    }
}
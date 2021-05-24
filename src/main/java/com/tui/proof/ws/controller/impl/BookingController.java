package com.tui.proof.ws.controller.impl;

import com.tui.proof.ws.controller.BookingProvider;
import com.tui.proof.ws.model.AvailabilityRequest;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.service.BookingService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value = "/test")
    public ResponseEntity<String> test() {
        log.info("Booking Controller");
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.test());
    }

    @Override
    @PostMapping(value = "/checkAvailability")
    public ResponseEntity<List<Flight>> checkAvailability(@RequestBody AvailabilityRequest request) {
        log.info("Availability Request: {}", request);

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.checkAvailability(request));

    }
}


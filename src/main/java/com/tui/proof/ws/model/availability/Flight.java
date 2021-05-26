package com.tui.proof.ws.model.availability;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * This class represents a flight
 */
@Data
@Accessors(chain = true)
public class Flight implements Comparable<Flight> {

    private String company;
    private Long flightNumber;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private String hour;
    private Monetary price;

    @Override
    public int compareTo(Flight o) {
        if (flightNumber.equals(o.getFlightNumber())) {
            return 0;
        }
        return -1;
    }
}

package com.tui.proof.ws.model.availability;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Flight implements Comparable<Flight> {

    private String company;
    private Long flightNumber;
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

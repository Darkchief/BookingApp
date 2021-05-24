package com.tui.proof.ws.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Flight {

    private String company;
    private Long flightNumber;
    private LocalDate date;
    private String hour;
    private Monetary price;
}

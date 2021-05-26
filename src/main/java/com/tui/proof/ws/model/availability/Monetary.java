package com.tui.proof.ws.model.availability;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * This class represents the price of the flight
 */
@Data
@Accessors(chain = true)
public class Monetary {

    private BigDecimal amount;
    private String currency;
}

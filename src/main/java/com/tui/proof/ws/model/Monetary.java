package com.tui.proof.ws.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Monetary {

    private BigDecimal amount;
    private String currency;
}

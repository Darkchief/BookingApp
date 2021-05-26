package com.tui.proof.ws.model.availability;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This class represents the number of people who will travel
 */
@Data
@Accessors(chain = true)
public class Paxes {

    private Integer infants;
    private Integer children;
    private Integer adults;
}

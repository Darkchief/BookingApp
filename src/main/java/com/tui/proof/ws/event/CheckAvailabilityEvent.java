package com.tui.proof.ws.event;

import com.tui.proof.ws.model.availability.Flight;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CheckAvailabilityEvent {

    private List<Flight> flights;

}

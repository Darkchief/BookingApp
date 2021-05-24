package com.tui.proof.ws.event.listener;

import com.tui.proof.ws.model.Flight;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CheckAvailabilityEvent {

    private List<Flight> flights;

}

package com.tui.proof.ws.event;


import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class AddNewReservationEvent {

    private Map<String, Reservation> reservationMap;
    private HolderRequest holderData;
}

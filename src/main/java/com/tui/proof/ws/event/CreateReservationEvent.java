package com.tui.proof.ws.event;


import com.tui.proof.ws.model.booking.HolderRequest;
import com.tui.proof.ws.model.booking.Reservation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class CreateReservationEvent {

    private Map<Long, Reservation> reservationMap;
    private HolderRequest holderData;
    private Long reservationCode;
}

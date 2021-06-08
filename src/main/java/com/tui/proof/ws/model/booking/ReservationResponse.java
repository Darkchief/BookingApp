package com.tui.proof.ws.model.booking;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReservationResponse {
    private Long reservationCode;
}

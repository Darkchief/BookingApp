package com.tui.proof.ws.model.booking;

import lombok.Data;

import java.util.List;

@Data
public class Holder {

    private String name;
    private String lastName;
    private String address;
    private String postalCode;
    private String country;
    private String email;
    private List<String> telephones;
}

package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class ExtendedAddress extends Address {
    private String streetAddition;

    public ExtendedAddress() {
    }

    public ExtendedAddress(String city, String country, String postalCode, String street, String streetAddition) {
        super(city, country, postalCode, street);
        this.streetAddition = streetAddition;
    }

    @ModelProperty
    public String getStreetAddition() {
        return streetAddition;
    }

    public void setStreetAddition(String streetAddition) {
        this.streetAddition = streetAddition;
    }
}
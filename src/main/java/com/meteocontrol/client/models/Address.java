package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class Address extends BaseModel {
    protected String city;
    protected String country;
    protected String postalCode;
    protected String street;

    public Address() {
    }

    public Address(String city, String country, String postalCode, String street) {
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.street = street;
    }

    @ModelProperty
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ModelProperty
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @ModelProperty
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @ModelProperty
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}

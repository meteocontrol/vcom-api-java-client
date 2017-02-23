package com.meteocontrol.client.models;

public class Address {
    private String city;
    private String country;
    private String postalCode;
    private String street;

    public Address() {
    }

    public Address(String city, String country, String postalCode, String street) {
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Address))
            return false;
        Address p = (Address)obj;
        return p.getCity().equals(this.city) &&
               p.getCountry().equals(this.country) &&
               p.getPostalCode().equals(this.postalCode) &&
               p.getStreet().equals(this.street);
    }
}

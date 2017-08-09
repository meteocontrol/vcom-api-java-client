package com.meteocontrol.client.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.Date;

public class SystemDetail extends BaseModel {
    private Address address;
    private int elevation;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="UTC")
    private Date commissionDate;
    private Coordinates coordinates;
    private String name;
    private Timezone timezone;

    public SystemDetail() {
    }

    public SystemDetail(Address address, int elevation, Date commissionDate, Coordinates coordinates, String name, Timezone timezone) {
        this.address = address;
        this.elevation = elevation;
        this.commissionDate = commissionDate;
        this.coordinates = coordinates;
        this.name = name;
        this.timezone = timezone;
    }

    @ModelProperty
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ModelProperty
    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    @ModelProperty
    public Date getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(Date commissionDate) {
        this.commissionDate = commissionDate;
    }

    @ModelProperty
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @ModelProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ModelProperty
    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }
}

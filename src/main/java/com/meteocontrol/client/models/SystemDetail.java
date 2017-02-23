package com.meteocontrol.client.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SystemDetail {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public Date getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(Date commissionDate) {
        this.commissionDate = commissionDate;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SystemDetail))
            return false;
        SystemDetail p = (SystemDetail)obj;
        return p.getName().equals(this.name) &&
               p.getAddress().equals(this.address) &&
               p.getCommissionDate().equals(this.commissionDate) &&
               p.getCoordinates().equals(this.coordinates) &&
               p.getElevation() == this.elevation &&
               p.getTimezone().equals(this.timezone);
    }
}

package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.Date;

public class MeasurementValue extends BaseModel {
    private Date timestamp;
    private String value;

    public MeasurementValue() {
    }

    public MeasurementValue(Date timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    @ModelProperty
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @ModelProperty
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

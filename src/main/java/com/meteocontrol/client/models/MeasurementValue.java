package com.meteocontrol.client.models;

import java.util.Date;

public class MeasurementValue {
    private Date timestamp;
    private String value;

    public MeasurementValue() {
    }

    public MeasurementValue(Date timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MeasurementValue))
            return false;
        MeasurementValue p = (MeasurementValue)obj;
        return p.getTimestamp().equals(this.timestamp) &&
                p.getValue().equals(this.value);
    }
}

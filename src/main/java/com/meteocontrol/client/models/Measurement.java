package com.meteocontrol.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashMap;

@JsonDeserialize(using = MeasurementJsonDeserializer.class)
public class Measurement {
    private String systemKey;
    private HashMap<String, MeasurementValue[]> values;

    public Measurement() {
    }

    public Measurement(String systemKey, HashMap<String, MeasurementValue[]> values) {
        this.systemKey = systemKey;
        this.values = values;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public HashMap<String, MeasurementValue[]> getAllValues() {
        return this.values;
    }

    public MeasurementValue[] getValuesByAbbreviation(String abbreviationId) {
        return this.values.get(abbreviationId);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Measurement))
            return false;
        Measurement p = (Measurement)obj;
        return p.getSystemKey().equals(this.systemKey) &&
                p.getAllValues().equals(this.values);
    }
}

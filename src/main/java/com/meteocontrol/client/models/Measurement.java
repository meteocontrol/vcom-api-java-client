package com.meteocontrol.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.HashMap;

@JsonDeserialize(using = MeasurementJsonDeserializer.class)
public class Measurement extends BaseModel {
    private String systemKey;
    private HashMap<String, MeasurementValue[]> values;

    public Measurement() {
    }

    public Measurement(String systemKey, HashMap<String, MeasurementValue[]> values) {
        this.systemKey = systemKey;
        this.values = values;
    }

    @ModelProperty
    public String getSystemKey() {
        return systemKey;
    }

    @ModelProperty
    public HashMap<String, MeasurementValue[]> getAllValues() {
        return this.values;
    }

    public MeasurementValue[] getValuesByAbbreviation(String abbreviationId) {
        return this.values.get(abbreviationId);
    }
}

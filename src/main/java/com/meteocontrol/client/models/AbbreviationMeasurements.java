package com.meteocontrol.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.HashMap;

@JsonDeserialize(using = AbbreviationMeasurementsJsonDeserializer.class)
public class AbbreviationMeasurements extends BaseModel {
    private HashMap<String, MeasurementValue[]> values;

    public AbbreviationMeasurements() {
    }

    public AbbreviationMeasurements(HashMap<String, MeasurementValue[]> values) {
        this.values = values;
    }

    @ModelProperty
    public HashMap<String, MeasurementValue[]> getAllValues() {
        return this.values;
    }

    public MeasurementValue[] getValuesByAbbreviation(String abbreviationId) {
        return this.values.get(abbreviationId);
    }

}

package com.meteocontrol.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashMap;

@JsonDeserialize(using = AbbreviationMeasurementsJsonDeserializer.class)
public class AbbreviationMeasurements {
    private HashMap<String, MeasurementValue[]> values;

    public AbbreviationMeasurements() {
    }

    public AbbreviationMeasurements(HashMap<String, MeasurementValue[]> values) {
        this.values = values;
    }

    public HashMap<String, MeasurementValue[]> getAllValues() {
        return this.values;
    }

    public MeasurementValue[] getValuesByAbbreviation(String abbreviationId) {
        return this.values.get(abbreviationId);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbbreviationMeasurements))
            return false;
        AbbreviationMeasurements p = (AbbreviationMeasurements)obj;
        return p.getAllValues().equals(this.values) ;
    }

}

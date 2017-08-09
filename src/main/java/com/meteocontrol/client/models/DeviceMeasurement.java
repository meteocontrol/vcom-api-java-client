package com.meteocontrol.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.HashMap;

@JsonDeserialize(using = DeviceMeasurementJsonDeserializer.class)
public class DeviceMeasurement extends BaseModel {
    private HashMap<String, AbbreviationMeasurements> measurement;

    public DeviceMeasurement() {
    }

    public DeviceMeasurement(HashMap<String, AbbreviationMeasurements> measurement) {
        this.measurement = measurement;
    }

    @ModelProperty
    public HashMap<String, AbbreviationMeasurements> getAllValues() {
        return this.measurement;
    }

    public AbbreviationMeasurements getDeviceMeasurementByDeviceId(String deviceId) {
        return this.measurement.get(deviceId);
    }
}

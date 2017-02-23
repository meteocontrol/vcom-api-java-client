package com.meteocontrol.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashMap;

@JsonDeserialize(using = DeviceMeasurementJsonDeserializer.class)
public class DeviceMeasurement {
    private HashMap<String, AbbreviationMeasurements> measurement;

    public DeviceMeasurement() {
    }

    public DeviceMeasurement(HashMap<String, AbbreviationMeasurements> measurement) {
        this.measurement = measurement;
    }

    public HashMap<String, AbbreviationMeasurements> getAllValues() {
        return this.measurement;
    }

    public AbbreviationMeasurements getDeviceMeasurementByDeviceId(String deviceId) {
        return this.measurement.get(deviceId);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceMeasurement))
            return false;
        DeviceMeasurement p = (DeviceMeasurement)obj;
        return p.getAllValues().equals(this.measurement) ;
    }

}

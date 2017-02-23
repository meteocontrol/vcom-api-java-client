package com.meteocontrol.client.endpoints.sub.systems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.calculations.Calculations;
import com.meteocontrol.client.endpoints.sub.systems.inverters.Inverter;
import com.meteocontrol.client.endpoints.sub.systems.inverters.Inverters;
import com.meteocontrol.client.endpoints.sub.systems.meters.Meters;
import com.meteocontrol.client.endpoints.sub.systems.sensors.Sensor;
import com.meteocontrol.client.endpoints.sub.systems.sensors.Sensors;
import com.meteocontrol.client.endpoints.sub.systems.technicalData.TechnicalData;
import com.meteocontrol.client.models.SystemDetail;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.systems.basics.Basics;
import com.meteocontrol.client.endpoints.sub.systems.bulk.Bulk;
import com.meteocontrol.client.endpoints.sub.systems.meters.Meter;
import com.meteocontrol.client.endpoints.sub.systems.stringboxes.Stringboxes;

import java.io.IOException;

public class System extends SubEndpoint {
    public System(EndpointInterface parent) {
        this.url = "";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public SystemDetail get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), SystemDetail.class);
    }

    public Basics basics() {
        return new Basics(this);
    }

    public Calculations calculations() {
        return new Calculations(this);
    }

    public Inverters inverters() {
        return new Inverters(this);
    }

    public Inverter inverter(String inverterId) {
        DeviceId device = new DeviceId(new Inverters(this), inverterId);
        return new Inverter(device);
    }

    public Inverter inverter(String[] inverterIds) {
        DeviceId device = new DeviceId(new Inverters(this), inverterIds);
        return new Inverter(device);
    }

    public Meters meters() {
        return new Meters(this);
    }

    public Meter meter(String meterId) {
        DeviceId device = new DeviceId(new Meters(this), meterId);
        return new Meter(device);
    }

    public Meter meter(String[] meterIds) {
        DeviceId device = new DeviceId(new Meters(this), meterIds);
        return new Meter(device);
    }

    public Sensors sensors() {
        return new Sensors(this);
    }

    public Sensor sensor(String sensorId) {
        DeviceId device = new DeviceId(new Sensors(this), sensorId);
        return new Sensor(device);
    }

    public Sensor sensor(String[] sensorIds) {
        DeviceId device = new DeviceId(new Sensors(this), sensorIds);
        return new Sensor(device);
    }

    public Stringboxes stringboxes() {
        return new Stringboxes(this);
    }

    public Bulk bulk() {
        return new Bulk(this);
    }

    public TechnicalData technicalData() {
        return new TechnicalData(this);
    }
}

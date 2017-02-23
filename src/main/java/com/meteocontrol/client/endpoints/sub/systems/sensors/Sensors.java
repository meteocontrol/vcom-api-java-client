package com.meteocontrol.client.endpoints.sub.systems.sensors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.system.DeviceBulk;
import com.meteocontrol.client.models.Sensor;

import java.io.IOException;


public class Sensors extends SubEndpoint {

    public Sensors(EndpointInterface parent) {
        this.url = "/sensors";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public Sensor[] get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), Sensor[].class);
    }

    public DeviceBulk bulk() {
        return new DeviceBulk(this);
    }
}
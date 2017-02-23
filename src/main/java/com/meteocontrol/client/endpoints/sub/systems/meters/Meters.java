package com.meteocontrol.client.endpoints.sub.systems.meters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.system.DeviceBulk;
import com.meteocontrol.client.models.Meter;

import java.io.IOException;

public class Meters extends SubEndpoint {

    public Meters(EndpointInterface parent) {
        this.url = "/meters";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public Meter[] get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), Meter[].class);
    }

    public DeviceBulk bulk() {
        return new DeviceBulk(this);
    }
}

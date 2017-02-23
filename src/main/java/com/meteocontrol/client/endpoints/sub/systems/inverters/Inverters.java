package com.meteocontrol.client.endpoints.sub.systems.inverters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.system.DeviceBulk;

import java.io.IOException;

public class Inverters extends SubEndpoint {

    public Inverters(EndpointInterface parent) {
        this.url = "/inverters";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public com.meteocontrol.client.models.Inverter[] get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.Inverter[].class);
    }

    public DeviceBulk bulk() {
        return new DeviceBulk(this);
    }
}

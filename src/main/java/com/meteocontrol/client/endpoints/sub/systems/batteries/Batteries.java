package com.meteocontrol.client.endpoints.sub.systems.batteries;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.system.DeviceBulk;
import com.meteocontrol.client.models.Battery;

import java.io.IOException;

public class Batteries extends SubEndpoint {

    public Batteries(EndpointInterface parent) {
        this.url = "/batteries";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public Battery[] get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), Battery[].class);
    }

    public DeviceBulk bulk() {
        return new DeviceBulk(this);
    }
}

package com.meteocontrol.client.endpoints.sub.systems.stringboxes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.system.DeviceBulk;

import java.io.IOException;

public class Stringboxes extends SubEndpoint {

    public Stringboxes(EndpointInterface parent) {
        this.url = "/stringboxes";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public com.meteocontrol.client.models.Stringbox[] get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.Stringbox[].class);
    }

    public DeviceBulk bulk() {
        return new DeviceBulk(this);
    }
}

package com.meteocontrol.client.endpoints.sub.systems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.EndpointInterface;

import java.io.IOException;

public class Abbreviation extends SubEndpoint {
    public Abbreviation(EndpointInterface parent) {
        this.url = "";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public com.meteocontrol.client.models.Abbreviation get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.Abbreviation.class);
    }

    public Measurements measurements() {
        return new Measurements(this);
    }
}

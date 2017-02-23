package com.meteocontrol.client.endpoints.sub.systems.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;

import java.io.IOException;

public class Abbreviation extends SubEndpoint {

    public Abbreviation(EndpointInterface parent) {
        this.url = "";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public Measurements measurements() {
        return new Measurements(this);
    }

    public com.meteocontrol.client.models.Abbreviation get() throws IOException {
        String json = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(json, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.Abbreviation.class);
    }
}

package com.meteocontrol.client.endpoints.sub.systems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;

import java.io.IOException;

public class Abbreviations extends SubEndpoint {
    public Abbreviations(EndpointInterface parent) {
        this.url = "/abbreviations";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public String[] get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), String[].class);
    }
}

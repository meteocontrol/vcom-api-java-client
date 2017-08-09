package com.meteocontrol.client.endpoints.sub.systems.responsibilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;

import java.io.IOException;

public class Responsibilities extends SubEndpoint {

    public Responsibilities(EndpointInterface parent) {
        this.url = "/responsibilities";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public com.meteocontrol.client.models.Responsibilities get() throws IOException {
        String responsibilitiesJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(responsibilitiesJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.Responsibilities.class);
    }
}

package com.meteocontrol.client.endpoints.sub.systems.technicalData;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.EndpointInterface;

import java.io.IOException;

public class TechnicalData extends SubEndpoint {

    public TechnicalData(EndpointInterface parent) {
        this.url = "/technical-data";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public com.meteocontrol.client.models.TechnicalData get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.TechnicalData.class);
    }
}

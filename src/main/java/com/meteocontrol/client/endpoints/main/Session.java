package com.meteocontrol.client.endpoints.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.ApiClient;

import java.io.IOException;

public class Session extends MainEndpoint {

    public Session(ApiClient client) {
        this.url = "/session";
        this.api = client;
    }

    public com.meteocontrol.client.models.Session get() throws IOException {
        String sessionJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(sessionJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.Session.class);
    }
}

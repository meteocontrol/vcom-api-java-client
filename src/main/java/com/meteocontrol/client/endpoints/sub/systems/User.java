package com.meteocontrol.client.endpoints.sub.systems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.filters.UserCriteria;
import com.meteocontrol.client.models.UserDetail;

import java.io.IOException;

public class User extends SubEndpoint {

    public User(EndpointInterface parent) {
        this.url = "";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public UserDetail get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), UserDetail.class);
    }
}

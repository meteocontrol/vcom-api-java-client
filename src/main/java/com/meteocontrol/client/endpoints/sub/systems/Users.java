package com.meteocontrol.client.endpoints.sub.systems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.filters.UserCriteria;
import com.meteocontrol.client.models.*;
import com.meteocontrol.client.models.User;

import java.io.IOException;

public class Users extends SubEndpoint {

    public Users(EndpointInterface parent) {
        this.url = "/users";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public com.meteocontrol.client.models.User[] get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.User[].class);
    }

    public UserDetail get(UserCriteria criteria) throws IOException {
        String systemJson = this.api.run(this.getUrl(), criteria.getAsList());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), UserDetail.class);
    }
}

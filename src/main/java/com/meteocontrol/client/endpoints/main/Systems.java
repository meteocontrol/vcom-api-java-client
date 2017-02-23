package com.meteocontrol.client.endpoints.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.sub.systems.Abbreviation;
import com.meteocontrol.client.endpoints.sub.systems.Abbreviations;
import com.meteocontrol.client.models.System;
import com.meteocontrol.client.ApiClient;
import com.meteocontrol.client.endpoints.sub.AbbreviationId;

import java.io.IOException;

public class Systems extends MainEndpoint {

    public Systems(ApiClient client) {
        this.url = "/systems";
        this.api = client;
    }

    public System[] get() throws IOException {
        String systemsJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemsJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), System[].class);
    }

    public Abbreviations abbreviations() {
        return new Abbreviations(this);
    }

    public Abbreviation abbreviation(String id) {
        Abbreviations abbreviations = new Abbreviations(this);
        AbbreviationId idEndpoint = new AbbreviationId(abbreviations, id);
        return new Abbreviation(idEndpoint);
    }
}

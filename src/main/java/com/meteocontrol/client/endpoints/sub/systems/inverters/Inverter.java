package com.meteocontrol.client.endpoints.sub.systems.inverters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.sub.systems.Abbreviations;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.AbbreviationId;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.system.Abbreviation;
import com.meteocontrol.client.models.InverterDetail;

import java.io.IOException;

public class Inverter extends SubEndpoint {
    public Inverter(EndpointInterface parent) {
        this.url = "";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public InverterDetail get() throws IOException {
        String systemJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), InverterDetail.class);
    }

    public Abbreviations abbreviations() {
        return new Abbreviations(this);
    }

    public Abbreviation abbreviation(String abbreviationId) {
        Abbreviations abbreviations = new Abbreviations(this);
        AbbreviationId abbreviationIdEndpoint = new AbbreviationId(abbreviations, abbreviationId);
        return new Abbreviation(abbreviationIdEndpoint);
    }

    public Abbreviation abbreviation(String[] abbreviationIds) {
        Abbreviations abbreviations = new Abbreviations(this);
        AbbreviationId abbreviationIdEndpoint = new AbbreviationId(abbreviations, abbreviationIds);
        return new Abbreviation(abbreviationIdEndpoint);
    }
}

package com.meteocontrol.client.endpoints.sub.systems.basics;

import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.AbbreviationId;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.Abbreviations;

public class Basics extends SubEndpoint {
    public Basics(EndpointInterface parent) {
        this.url = "/basics";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public Abbreviations abbreviations() {
        return new Abbreviations(this);
    }

    public Abbreviation abbreviation(String abbreviationId) {
        Abbreviations abbreviations = new Abbreviations(this);
        AbbreviationId abbreviationIdEndpoint = new AbbreviationId(abbreviations, abbreviationId);
        return new Abbreviation(abbreviationIdEndpoint);
    }

    public BasicBulk bulk() {
        return new BasicBulk(this);
    }
}

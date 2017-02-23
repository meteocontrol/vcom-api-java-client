package com.meteocontrol.client.endpoints.sub.systems.calculations;

import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.Abbreviations;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.AbbreviationId;

public class Calculations extends SubEndpoint {
    public Calculations(EndpointInterface parent) {
        this.url = "/calculations";
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

    public CalculationBulk bulk() {
        return new CalculationBulk(this);
    }
}

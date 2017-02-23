package com.meteocontrol.client.endpoints.sub.systems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.filters.MeasurementsCriteria;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.models.Measurement;

import java.io.IOException;

public class Measurements extends SubEndpoint {
    public Measurements(EndpointInterface parent) {
        this.url = "/measurements";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public Measurement[] get(MeasurementsCriteria criteria) throws IOException {
        String systemJson = this.api.run(this.getUrl(), criteria.getAsList());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(systemJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), Measurement[].class);
    }
}

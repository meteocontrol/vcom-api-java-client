package com.meteocontrol.client.endpoints.sub.tickets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.models.TicketHistory;

import java.io.IOException;

public class TicketHistories extends SubEndpoint {
    public TicketHistories(EndpointInterface parent) {
        this.url = "/histories";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public TicketHistory[] get() throws IOException {
        String json = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(json, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), TicketHistory[].class);
    }
}

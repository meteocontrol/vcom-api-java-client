package com.meteocontrol.client.endpoints.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meteocontrol.client.filters.TicketsCriteria;
import com.meteocontrol.client.models.Ticket;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.ApiClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Tickets extends MainEndpoint {

    public Tickets(ApiClient client) {
        this.url = "/tickets";
        this.api = client;
    }

    public Ticket[] find(TicketsCriteria ticketsCriteria) throws IOException {
        String ticketsJson = this.api.run(this.getUrl(), ticketsCriteria.getAsList());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(ticketsJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), Ticket[].class);
    }

    public int create(Ticket ticket) throws IOException {
        if (ticket == null || !ticket.isValid()) {
            throw new IllegalArgumentException("Ticket is invalid!");
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        root.put("systemKey", ticket.getDesignation());
        root.put("designation", ticket.getSummary());
        root.put("date", dateFormat.format(ticket.getDate()));

        if (ticket.getSummary() != null) root.put("summary", ticket.getSummary());
        if (ticket.getSummary() != null) root.put("status", ticket.getStatus().toString());
        if (ticket.getSummary() != null) root.put("priority", ticket.getPriority().toString());
        if (ticket.getSummary() != null) root.put("includeInReports", ticket.getIncludeInReports().toString());

        String responseJson = this.api.run(this.getUrl(), null, root.toString(), ApiMethods.POST);
        JsonNode rootNode = mapper.readValue(responseJson, JsonNode.class);
        return Integer.parseInt(rootNode.get("data").get("ticketId").toString());
    }
}

package com.meteocontrol.client.endpoints.sub.tickets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.params.ApiMethods;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Ticket extends SubEndpoint {
    public Ticket(EndpointInterface parent) {
        this.url = "";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public com.meteocontrol.client.models.Ticket get() throws IOException {
        String commentsJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(commentsJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.Ticket.class);
    }

    public void update(com.meteocontrol.client.models.Ticket ticket) {
        if (ticket == null || !ticket.isValid()) {
            throw new IllegalArgumentException("Ticket is invalid!");
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        root.put("designation", ticket.getDesignation());
        root.put("description", ticket.getDescription());
        root.put("summary", ticket.getSummary());
        root.put("date", dateFormat.format(ticket.getDate()));
        root.put("includeInReports", ticket.getIncludeInReports().toString());
        root.put("status", ticket.getStatus().toString());
        root.put("priority", ticket.getPriority().toString());

        this.api.run(this.getUrl(), null, root.toString(), ApiMethods.PATCH);
    }

    public void delete() {
        this.api.run(this.getUrl(), null, null, ApiMethods.DELETE);
    }

    public Comments comments() {
        return new Comments(this);
    }

    public Comment comment(int commentId) {
        Comments comments = new Comments(this);
        CommentId commentIdEndpoint = new CommentId(comments, String.valueOf(commentId));
        return new Comment(commentIdEndpoint);
    }

    public Attachments attachments() {
        return new Attachments(this);
    }

    public Attachment attachment(int attachmentId) {
        Attachments attachments = new Attachments(this);
        AttachmentId attachmentIdEndpoint = new AttachmentId(attachments, String.valueOf(attachmentId));
        return new Attachment(attachmentIdEndpoint);
    }

    public TicketHistories histories() {
        return new TicketHistories(this);
    }
}
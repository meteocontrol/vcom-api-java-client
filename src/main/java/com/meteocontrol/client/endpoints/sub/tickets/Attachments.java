package com.meteocontrol.client.endpoints.sub.tickets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.models.*;
import com.meteocontrol.client.models.Attachment;
import com.meteocontrol.client.params.ApiMethods;

import java.io.IOException;
import java.lang.System;

public class Attachments extends SubEndpoint {
    public Attachments(EndpointInterface parent) {
        this.url = "/attachments";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public Attachment[] get() throws IOException {
        String AttachmentJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(AttachmentJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), com.meteocontrol.client.models.Attachment[].class);
    }

    public Attachment create(AttachmentFile file) throws IOException {
        if (file == null || !file.isValid()) {
            throw new IllegalArgumentException("Attachment is invalid!");
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("filename", file.getFilename());
        root.put("content", file.getContent());
        String responseJson = this.api.run(this.getUrl(), null, root.toString(), ApiMethods.POST);
        JsonNode rootNode = mapper.readValue(responseJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), Attachment.class);
    }
}

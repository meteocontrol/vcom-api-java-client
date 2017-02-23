package com.meteocontrol.client.endpoints.sub.tickets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.models.CommentDetail;
import com.meteocontrol.client.params.ApiMethods;

import java.io.IOException;

public class Comment extends SubEndpoint {
    public Comment(EndpointInterface parent) {
        this.url = "";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public CommentDetail get() throws IOException {
        String commentJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(commentJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), CommentDetail.class);
    }

    public void update(CommentDetail commentDetail) {
        if (commentDetail == null || !commentDetail.isValid()) {
            throw new IllegalArgumentException("Comment is invalid!");
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("comment", commentDetail.getComment());
        this.api.run(this.getUrl(), null, root.toString(), ApiMethods.PATCH);
    }

    public void delete() {
        this.api.run(this.getUrl(), null, null, ApiMethods.DELETE);
    }
}
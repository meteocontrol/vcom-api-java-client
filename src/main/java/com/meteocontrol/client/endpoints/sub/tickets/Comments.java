package com.meteocontrol.client.endpoints.sub.tickets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.models.*;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.models.Comment;
import com.meteocontrol.client.params.ApiMethods;

import java.io.IOException;

public class Comments extends SubEndpoint {
    public Comments(EndpointInterface parent) {
        this.url = "/comments";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public com.meteocontrol.client.models.Comment[] get() throws IOException {
        String commentsJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(commentsJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), Comment[].class);
    }

    public int create(CommentDetail commentDetail) throws IOException {
        if (commentDetail == null || !commentDetail.isValid()) {
            throw new IllegalArgumentException("Comment is invalid!");
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("comment", commentDetail.getComment());
        String responseJson = this.api.run(this.getUrl(), null, root.toString(), ApiMethods.POST);
        JsonNode rootNode = mapper.readValue(responseJson, JsonNode.class);
        return Integer.parseInt(rootNode.get("data").get("commentId").toString());
    }
}
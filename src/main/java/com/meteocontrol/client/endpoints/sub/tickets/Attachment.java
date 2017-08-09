package com.meteocontrol.client.endpoints.sub.tickets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.models.AttachmentFile;
import java.io.IOException;

public class Attachment extends SubEndpoint {
    public Attachment(EndpointInterface parent) {
        this.url = "";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public AttachmentFile get() throws IOException {
        String attachmentJson = this.api.run(this.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(attachmentJson, JsonNode.class);
        return mapper.readValue(rootNode.get("data").toString(), AttachmentFile.class);
    }
}

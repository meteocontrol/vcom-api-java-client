package com.meteocontrol.client.endpoints.sub.tickets;

import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;

public class AttachmentId extends SubEndpoint {
    public AttachmentId(EndpointInterface parent, String id) {
        this.url = "/" + id;
        this.parent = parent;
        this.api = parent.getApiClient();
    }
}

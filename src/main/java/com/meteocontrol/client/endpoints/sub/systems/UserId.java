package com.meteocontrol.client.endpoints.sub.systems;

import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;

public class UserId extends SubEndpoint {
    public UserId(EndpointInterface parent, String idString) {
        this.url = "/" + idString;
        this.api = parent.getApiClient();
        this.parent = parent;
    }
}

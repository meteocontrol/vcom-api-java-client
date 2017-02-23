package com.meteocontrol.client.endpoints.sub.systems;


import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;

public class SystemId extends SubEndpoint {
    public SystemId(EndpointInterface parent, String id) {
        this.url = "/" + id;
        this.parent = parent;
        this.api = parent.getApiClient();
    }
}

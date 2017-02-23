package com.meteocontrol.client.endpoints.sub.systems.bulk;

import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;

public class Bulk extends SubEndpoint {

    public Bulk(EndpointInterface parent) {
        this.url = "/bulk";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public Measurements measurements() {
        return new Measurements(this);
    }
}

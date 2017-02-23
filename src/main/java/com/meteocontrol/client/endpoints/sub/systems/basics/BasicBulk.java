package com.meteocontrol.client.endpoints.sub.systems.basics;

import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;

public class BasicBulk extends SubEndpoint {
    public BasicBulk(EndpointInterface parent) {
        this.url = "/bulk";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public BasicBulkMeasurements measurements() {
        return new BasicBulkMeasurements(this);
    }
}

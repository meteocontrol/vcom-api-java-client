package com.meteocontrol.client.endpoints.sub.systems.system;

import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;


public class DeviceBulk extends SubEndpoint {
    public DeviceBulk(EndpointInterface parent) {
        this.url = "/bulk";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public DeviceBulkMeasurements measurements() {
        return new DeviceBulkMeasurements(this);
    }

}

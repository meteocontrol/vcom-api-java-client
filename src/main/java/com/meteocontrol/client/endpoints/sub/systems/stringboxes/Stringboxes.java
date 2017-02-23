package com.meteocontrol.client.endpoints.sub.systems.stringboxes;

import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.sub.systems.system.DeviceBulk;

public class Stringboxes extends SubEndpoint {

    public Stringboxes(EndpointInterface parent) {
        this.url = "/stringboxes";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public DeviceBulk bulk() {
        return new DeviceBulk(this);
    }
}

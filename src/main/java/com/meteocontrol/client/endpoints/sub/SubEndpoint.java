package com.meteocontrol.client.endpoints.sub;

import com.meteocontrol.client.endpoints.Endpoint;

public class SubEndpoint extends Endpoint {
    public String getUrl() {
        return this.parent.getUrl() + this.url;
    }
}

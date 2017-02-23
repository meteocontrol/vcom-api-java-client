package com.meteocontrol.client.endpoints;

import com.meteocontrol.client.ApiClient;

public abstract class Endpoint implements EndpointInterface {
    protected ApiClient api;
    protected String url;
    protected EndpointInterface parent;

    public abstract String getUrl();

    public ApiClient getApiClient() {
        return this.api;
    }
}

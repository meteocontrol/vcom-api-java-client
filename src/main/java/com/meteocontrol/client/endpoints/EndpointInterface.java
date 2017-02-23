package com.meteocontrol.client.endpoints;

import com.meteocontrol.client.ApiClient;

public interface EndpointInterface {
    String getUrl();
    ApiClient getApiClient();
}

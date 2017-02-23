package com.meteocontrol.client;

public class Factory {
    public ApiClient getApiClient() {
        Config config = new Config();
        return new ApiClient(new HttpClient(config));
    }

    public ApiClient getApiClient(Config config) {
        config.validate();
        return new ApiClient(new HttpClient(config));
    }
}

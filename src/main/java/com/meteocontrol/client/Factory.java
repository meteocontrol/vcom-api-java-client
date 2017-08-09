package com.meteocontrol.client;

import com.meteocontrol.client.handlers.AuthorizationHandlerInterface;
import com.meteocontrol.client.handlers.BasicAuthorizationHandler;
import com.meteocontrol.client.handlers.OAuthAuthorizationHandler;

public class Factory {
    public ApiClient getApiClient() {
        Config config = new Config();
        return new ApiClient(new HttpClient(config, this.getAuthorizationHandler(config)));
    }

    public ApiClient getApiClient(Config config) {
        config.validate();
        return new ApiClient(new HttpClient(config, this.getAuthorizationHandler(config)));
    }

    private AuthorizationHandlerInterface getAuthorizationHandler(Config config) {
        return config.getApiAuthorizationMode().equals("basic") ?
                new BasicAuthorizationHandler(config) :
                new OAuthAuthorizationHandler(config);
    }
}

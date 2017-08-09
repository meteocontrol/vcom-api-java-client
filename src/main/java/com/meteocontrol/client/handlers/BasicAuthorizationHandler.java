package com.meteocontrol.client.handlers;

import com.meteocontrol.client.Config;
import com.meteocontrol.client.exceptions.UnauthorizedException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHeader;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

public class BasicAuthorizationHandler implements AuthorizationHandlerInterface {
    private Config config;

    public BasicAuthorizationHandler(Config config) {
        this.config = config;
    }

    @Override
    public void handleUnauthorizedException(String message, int code, HttpClient client) {
        throw new UnauthorizedException(message, code);
    }

    @Override
    public Header[] appendAuthorizationHeader(HttpClient client, Header[] headers) throws UnsupportedEncodingException {
        return ArrayUtils.addAll(headers, new BasicHeader("Authorization", this.getBasicAuthString()));
    }

    private String getBasicAuthString() throws UnsupportedEncodingException {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] payload = (this.config.getApiUsername() + ":" + this.config.getApiPassword()).getBytes("UTF-8");
        return "Basic " + encoder.encode(payload);
    }
}

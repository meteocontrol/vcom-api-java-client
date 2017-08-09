package com.meteocontrol.client.handlers;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import java.io.UnsupportedEncodingException;

public interface AuthorizationHandlerInterface {
    void handleUnauthorizedException(String message, int code, HttpClient client);
    Header[] appendAuthorizationHeader(HttpClient client, Header[] headers) throws UnsupportedEncodingException;
}

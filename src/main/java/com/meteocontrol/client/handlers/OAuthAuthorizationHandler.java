package com.meteocontrol.client.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteocontrol.client.Config;
import com.meteocontrol.client.exceptions.ApiClientException;
import com.meteocontrol.client.exceptions.UnauthorizedException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OAuthAuthorizationHandler implements AuthorizationHandlerInterface {
    private String accessToken;
    private String refreshToken;
    private Config config;

    public OAuthAuthorizationHandler(Config config) {
        this.config = config;
    }

    @Override
    public void handleUnauthorizedException(String message, int code, HttpClient client) {
        this.doOAuthRefresh(client);
    }

    @Override
    public Header[] appendAuthorizationHeader(HttpClient client, Header[] headers) throws UnsupportedEncodingException {
        if(this.accessToken == null || !this.accessToken.isEmpty()) {
            this.doOAuthGrant(client);
        }
        return ArrayUtils.addAll(headers, new BasicHeader("Authorization", "Bearer " + this.accessToken));
    }

    private void doOAuthGrant(HttpClient client) {
        List<NameValuePair> formData = new ArrayList<>();
        formData.add(new BasicNameValuePair("grant_type", "password"));
        formData.add(new BasicNameValuePair("username", this.config.getApiUsername()));
        formData.add(new BasicNameValuePair("password", this.config.getApiPassword()));
        this.doOAuthRequest(client, formData);
    }

    private void doOAuthRefresh(HttpClient client) {
        List<NameValuePair> formData = new ArrayList<>();
        formData.add(new BasicNameValuePair("grant_type", "refresh_token"));
        formData.add(new BasicNameValuePair("refresh_token", this.refreshToken));
        this.doOAuthRequest(client, formData);
    }

    private void doOAuthRequest(HttpClient client, List<NameValuePair> formData) {
        URL url = null;
        try {
            url = new URL(this.config.getApiUrl());
            URI baseUri = new URIBuilder()
                    .setScheme(url.getProtocol())
                    .setHost(url.getHost())
                    .setPath(url.getPath())
                    .build();
            HttpPost postRequest = new HttpPost(baseUri.toURL() + "/login");
            postRequest.setHeader(new BasicHeader("X-API-KEY", this.config.getApiKey()));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formData);
            postRequest.setEntity(entity);

            HttpResponse response = client.execute(postRequest);
            String responseContent = EntityUtils.toString(response.getEntity());

            if (response.getStatusLine().getStatusCode() >= 400) {
                throw response.getStatusLine().getStatusCode() == 401 ?
                        new UnauthorizedException(responseContent, 401) :
                        new ApiClientException(responseContent, response.getStatusLine().getStatusCode());
            }

            JsonNode rootNode = new ObjectMapper().readTree(new StringReader(responseContent));
            this.accessToken = rootNode.get("access_token").asText();
            this.refreshToken = rootNode.get("refresh_token").asText();
        } catch (MalformedURLException | UnsupportedEncodingException | URISyntaxException ignored) {
        } catch (IOException e) {
            throw new ApiClientException(e.getMessage());
        }
    }
}

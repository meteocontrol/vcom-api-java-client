package com.meteocontrol.client;

import com.meteocontrol.client.exceptions.ApiClientException;
import com.meteocontrol.client.params.ApiMethods;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class HttpClient {
    private Config config;
    private URI baseUri;
    private Header[] headers;
    private HttpClientContext context;
    private org.apache.http.client.HttpClient client;

    public HttpClient(Config config) {
        this.config = config;
        URL url;
        try {
            url = new URL(this.config.getApiUrl());
            this.baseUri = new URIBuilder()
                    .setScheme(url.getProtocol())
                    .setHost(url.getHost())
                    .setPath(url.getPath())
                    .build();
            this.createHttpClient();
            this.createDefaultHeaders();
        } catch (MalformedURLException e) {
            throw new ApiClientException("URL is broken");
        } catch (URISyntaxException e) {
            throw new ApiClientException("URI cannot be built");
        } catch (UnsupportedEncodingException e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    public String execute(ApiMethods method, String path, List<? extends NameValuePair> queryParameters, String body) {
        HttpRequestBase request;
        try {
            String url = this.baseUri.toURL() + path;
            if (queryParameters != null) {
                url += "?" + URLEncodedUtils.format(queryParameters, "UTF-8");
            }

            request = this.prepareRequest(method, url, body);
            request.setHeaders(this.headers);

            HttpResponse response = this.client.execute(request, this.context);
            HttpEntity entity = response.getEntity();

            if (this.isLimitationReached(response)) {
                this.waitUntilReset(response);
            }
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            throw new ApiClientException(e.getMessage());
        }
    }

    private void waitUntilReset(HttpResponse response) throws InterruptedException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat();
        dateFormatter.applyPattern("E, d M Y H:i:s 'GMT'");
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        dateFormatter.setTimeZone(gmtTime);
        try {
            Date requestTime = dateFormatter.parse(response.getFirstHeader("Date").getValue());
            Date resetTime = dateFormatter.parse(response.getFirstHeader("X-RateLimit-Reset-Minute").getValue());
            Thread.sleep(resetTime.getTime() - requestTime.getTime());
        } catch (ParseException ex) {
            throw new ApiClientException("Cannot parse mc minute rate header");
        }
    }

    private HttpRequestBase prepareRequest(ApiMethods method, String url, String body) {
        HttpEntity entity;
        switch (method) {
            case GET:
                return new HttpGet(url);
            case DELETE:
                return new HttpDelete(url);
            case PATCH:
                HttpPatch patchRequest = new HttpPatch(url);
                entity = new StringEntity(body, ContentType.create("application/json"));
                patchRequest.setEntity(entity);
                return patchRequest;
            case POST:
                HttpPost postRequest = new HttpPost(url);
                entity = new StringEntity(body, ContentType.create("application/json"));
                postRequest.setEntity(entity);
                return postRequest;
            default:
                throw new ApiClientException("Unsupported Method!");
        }
    }

    private void createHttpClient() {
        UsernamePasswordCredentials credentials;
        BasicCredentialsProvider credentialsProvider;

        credentials = new UsernamePasswordCredentials(
                config.getApiUsername(),
                config.getApiPassword()
        );
        credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(this.baseUri.getHost(), AuthScope.ANY_PORT),
                credentials
        );
        this.context = HttpClientContext.create();
        this.context.setCredentialsProvider(credentialsProvider);
        this.client = HttpClients.createDefault();
    }

    private void createDefaultHeaders() throws UnsupportedEncodingException {
        this.headers = new Header[]{
                new BasicHeader("X-API-KEY", this.config.getApiKey()),
                new BasicHeader("Authorization", this.getBasicAuthString()),
                new BasicHeader("Accept", "*/*")
        };
    }

    private String getBasicAuthString() throws UnsupportedEncodingException {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] payload = (this.config.getApiUsername() + ":" + this.config.getApiPassword()).getBytes("UTF-8");
        return "Basic " + encoder.encode(payload);
    }

    private boolean isLimitationReached(HttpResponse response) {
        return response.containsHeader("X-RateLimit-Remaining-Minute") &&
                Integer.parseInt(response.getFirstHeader("X-RateLimit-Remaining-Minute").getValue()) <= 1;
    }
}

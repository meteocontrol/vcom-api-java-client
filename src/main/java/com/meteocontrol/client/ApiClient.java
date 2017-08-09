package com.meteocontrol.client;

import com.meteocontrol.client.endpoints.main.Session;
import com.meteocontrol.client.endpoints.main.Systems;
import com.meteocontrol.client.endpoints.main.Tickets;
import com.meteocontrol.client.endpoints.sub.systems.System;
import com.meteocontrol.client.endpoints.sub.systems.SystemId;
import com.meteocontrol.client.endpoints.sub.tickets.TicketId;
import com.meteocontrol.client.params.ApiMethods;
import com.meteocontrol.client.endpoints.sub.tickets.Ticket;
import com.meteocontrol.client.params.AuthorizationMode;
import org.apache.http.NameValuePair;

import java.util.List;

public class ApiClient {
    private HttpClient client;

    public Systems systems;
    public Tickets tickets;

    public static ApiClient get(String username, String password, String apiKey) {
        Factory factory = new Factory();
        Config config = new Config();
        config.setApiUsername(username);
        config.setApiPassword(password);
        config.setApiKey(apiKey);
        config.setApiAuthorizationMode(AuthorizationMode.BASIC.toString());
        return factory.getApiClient(config);
    }

    public static ApiClient get(String username, String password, String apiKey, AuthorizationMode apiAuthMode) {
        Factory factory = new Factory();
        Config config = new Config();
        config.setApiUsername(username);
        config.setApiPassword(password);
        config.setApiKey(apiKey);
        config.setApiAuthorizationMode(apiAuthMode.toString());
        return factory.getApiClient(config);
    }

    public ApiClient(HttpClient client) {
        this.client = client;
        this.systems = new Systems(this);
        this.tickets = new Tickets(this);
    }

    public Session session() {
        return new Session(this);
    }

    public System system(String systemKey) {
        Systems systems = new Systems(this);
        return new System(new SystemId(systems, systemKey));
    }

    public Ticket ticket(String ticketId) {
        Tickets tickets = new Tickets(this);
        return new Ticket(new TicketId(tickets, ticketId));
    }

    public String run(String url) {
        return this.run(url, null, null, ApiMethods.GET);
    }

    public String run(String url, List<? extends NameValuePair> queryParameters) {
        return this.run(url, queryParameters, null, ApiMethods.GET);
    }

    public String run(String url, String body, ApiMethods method) {
        return this.run(url, null, body, method);
    }

    public String run(String url, List<? extends NameValuePair> queryParameters, String body, ApiMethods method) {
        return this.client.execute(method, url, queryParameters, body);
    }
}

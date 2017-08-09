package com.meteocontrol.client.filters;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCriteria {

    private Map<String, String> filters;

    public UserCriteria() {
        this.filters = new HashMap<>();
    }

    public String getUserName() {
        return this.filters.get("username");
    }

    public UserCriteria withUserName(String username) {
        this.filters.put("username", username);
        return this;
    }

    public List<NameValuePair> getAsList() {
        List<NameValuePair> list = new ArrayList<NameValuePair>(filters.size());
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return list;
    }
}

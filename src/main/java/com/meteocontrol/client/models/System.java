package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class System extends BaseModel {
    private String key;
    private String name;

    public System() {
    }

    public System(String key, String name) {
        this.key = key;
        this.name = name;
    }

    @ModelProperty
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @ModelProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

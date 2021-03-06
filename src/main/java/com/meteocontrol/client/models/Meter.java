package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class Meter extends BaseModel {

    private String id;
    private String name;

    public Meter() {
    }

    public Meter(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @ModelProperty
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ModelProperty
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

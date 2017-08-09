package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class SensorDetail extends BaseModel {

    private String id;
    private String name;
    private String address;

    public SensorDetail() {
    }

    public SensorDetail(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    @ModelProperty
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

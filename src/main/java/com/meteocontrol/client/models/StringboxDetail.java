package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class StringboxDetail extends BaseModel {

    private String id;
    private String name;
    private String serial;
    private float scaleFactor;

    public StringboxDetail() {
    }

    public StringboxDetail(String id, String name, String serial, float scaleFactor) {
        this.id = id;
        this.name = name;
        this.serial = serial;
        this.scaleFactor = scaleFactor;
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
    public String getSerial() {
        return this.serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @ModelProperty
    public float getScaleFactor() { return this.scaleFactor; }

    public void setScaleFactor(float scaleFactor) { this.scaleFactor = scaleFactor; }
}

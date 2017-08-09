package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class Timezone extends BaseModel {
    private String name;
    private String utcOffset;

    public Timezone() {
    }

    public Timezone(String name, String utcOffset) {
        this.name = name;
        this.utcOffset = utcOffset;
    }

    @ModelProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ModelProperty
    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }
}

package com.meteocontrol.client.models;

public class Timezone {
    private String name;
    private String utcOffset;

    public Timezone() {
    }

    public Timezone(String name, String utcOffset) {
        this.name = name;
        this.utcOffset = utcOffset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Timezone))
            return false;
        Timezone p = (Timezone)obj;
        return p.getName().equals(this.name) &&
               p.getUtcOffset().equals(this.utcOffset);
    }
}

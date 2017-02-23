package com.meteocontrol.client.models;

public class Sensor {

    private String id;
    private String name;

    public Sensor() {
    }

    public Sensor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sensor))
            return false;
        Sensor p = (Sensor)obj;
        return p.getId().equals(this.id) &&
                p.getName().equals(this.name);
    }
}

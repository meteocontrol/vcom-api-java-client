package com.meteocontrol.client.models;

public class Inverter {

    private String id;
    private String name;
    private String serial;

    public Inverter() {
    }

    public Inverter(String id, String name, String serial) {
        this.id = id;
        this.name = name;
        this.serial = serial;
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


    public String getSerial() {
        return this.serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Inverter))
            return false;
        Inverter p = (Inverter)obj;
        return p.getId().equals(this.id) &&
                p.getSerial().equals(this.serial) &&
                p.getName().equals(this.name);
    }
}

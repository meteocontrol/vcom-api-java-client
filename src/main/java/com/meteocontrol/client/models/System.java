package com.meteocontrol.client.models;

public class System {
    private String key;
    private String name;

    public System() {
    }

    public System(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof System))
            return false;
        System p = (System)obj;
        return p.getKey().equals(this.key) &&
                p.getName().equals(this.name);
    }
}

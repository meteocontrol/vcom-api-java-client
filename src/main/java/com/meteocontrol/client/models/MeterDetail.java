package com.meteocontrol.client.models;

public class MeterDetail {

    private String id;
    private String name;
    private String address;

    public MeterDetail() {
    }

    public MeterDetail(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MeterDetail))
            return false;
        MeterDetail p = (MeterDetail) obj;
        return p.getId().equals(this.id) &&
                p.getName().equals(this.name) &&
                p.getAddress().equals(this.address);
    }
}

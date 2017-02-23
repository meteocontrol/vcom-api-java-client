package com.meteocontrol.client.models;

public class InverterDetail {
    private String id;
    private String model;
    private String vendor;
    private String serial;
    private String name;

    public InverterDetail() {
    }

    public InverterDetail(String id, String model, String vendor, String serial, String name) {
        this.id = id;
        this.model = model;
        this.vendor = vendor;
        this.serial = serial;
        this.name = name;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
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
        if (!(obj instanceof InverterDetail))
            return false;
        InverterDetail p = (InverterDetail) obj;
        return p.getId().equals(this.id) &&
                p.getName().equals(this.name) &&
                p.getVendor().equals(this.vendor) &&
                p.getSerial().equals(this.serial) &&
                p.getModel().equals(this.model);
    }
}

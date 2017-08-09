package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

public class InverterDetail extends BaseModel {
    private String id;
    private String model;
    private String vendor;
    private String serial;
    private String name;
    private float scaleFactor;

    public InverterDetail() {
    }

    public InverterDetail(String id, String model, String vendor, String serial, String name, float scaleFactor) {
        this.id = id;
        this.model = model;
        this.vendor = vendor;
        this.serial = serial;
        this.name = name;
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
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @ModelProperty
    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
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
    public float getScaleFactor() { return scaleFactor; }

    public void setScaleFactor(float scaleFactor) { this.scaleFactor = scaleFactor; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InverterDetail))
            return false;
        InverterDetail p = (InverterDetail) obj;
        return p.getId().equals(this.id) &&
                p.getName().equals(this.name) &&
                p.getVendor().equals(this.vendor) &&
                p.getSerial().equals(this.serial) &&
                p.getModel().equals(this.model) &&
                Float.compare(p.getScaleFactor(), this.scaleFactor) == 0;
    }
}

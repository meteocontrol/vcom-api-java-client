package com.meteocontrol.client.models;


import com.meteocontrol.client.models.annotation.ModelProperty;

public class TechnicalDevice extends BaseModel {
    private String vendor;
    private String model;
    private String count;

    public TechnicalDevice() {
    }

    public TechnicalDevice(String vendor, String model, String count) {
        this.vendor = vendor;
        this.model = model;
        this.count = count;
    }

    @ModelProperty
    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @ModelProperty
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @ModelProperty
    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

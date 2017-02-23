package com.meteocontrol.client.models;


public class TechnicalDevice {
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

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TechnicalDevice))
            return false;
        TechnicalDevice p = (TechnicalDevice) obj;
        return p.getCount().equals(this.count) &&
                p.getModel().equals(this.model) &&
                p.getVendor().equals(this.vendor);
    }
}

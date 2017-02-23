package com.meteocontrol.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashMap;

@JsonDeserialize(using = TechnicalDataJsonDeserializer.class)
public class TechnicalData {

    private String nominalPower;
    private String siteArea;
    private TechnicalDevice[] panels;
    private TechnicalDevice[] inverters;
    private HashMap<Integer, TechnicalDevice[]> values;

    public TechnicalData() {
    }

    public TechnicalData(String nominalPower, String siteArea, TechnicalDevice[] panels, TechnicalDevice[] inverters) {
        this.nominalPower = nominalPower;
        this.siteArea = siteArea;
        this.panels = panels;
        this.inverters = inverters;
    }

    public String getNominalPower() {
        return this.nominalPower;
    }

    public void setNominalPower(String nominalPower) {
        this.nominalPower = nominalPower;
    }

    public String getSiteArea() {
        return this.siteArea;
    }

    public void setSiteArea(String siteArea) {
        this.siteArea = siteArea;
    }

    public TechnicalDevice[] getAllPanels() {
        return this.panels;
    }

    public TechnicalDevice getPanelByIndex(int index) {
        return this.panels[index];
    }

    public TechnicalDevice[] getAllInverters() {
        return this.inverters;
    }

    public TechnicalDevice getInverterByIndex(int index) {
        return this.inverters[index];
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TechnicalData))
            return false;
        TechnicalData p = (TechnicalData) obj;
        return p.getNominalPower().equals(this.nominalPower) &&
                p.getSiteArea().equals(this.siteArea) &&
                p.getAllInverters().equals(this.inverters) &&
                p.getAllPanels().equals(this.panels);
    }
}

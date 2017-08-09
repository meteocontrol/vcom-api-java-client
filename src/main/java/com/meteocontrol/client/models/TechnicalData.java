package com.meteocontrol.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.HashMap;

@JsonDeserialize(using = TechnicalDataJsonDeserializer.class)
public class TechnicalData extends BaseModel {

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

    @ModelProperty
    public String getNominalPower() {
        return this.nominalPower;
    }

    public void setNominalPower(String nominalPower) {
        this.nominalPower = nominalPower;
    }

    @ModelProperty
    public String getSiteArea() {
        return this.siteArea;
    }

    public void setSiteArea(String siteArea) {
        this.siteArea = siteArea;
    }

    @ModelProperty
    public TechnicalDevice[] getAllPanels() {
        return this.panels;
    }

    public TechnicalDevice getPanelByIndex(int index) {
        return this.panels[index];
    }

    @ModelProperty
    public TechnicalDevice[] getAllInverters() {
        return this.inverters;
    }

    public TechnicalDevice getInverterByIndex(int index) {
        return this.inverters[index];
    }
}

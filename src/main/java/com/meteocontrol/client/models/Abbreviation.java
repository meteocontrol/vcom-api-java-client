package com.meteocontrol.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meteocontrol.client.models.annotation.ModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Abbreviation extends BaseModel {
    private String aggregation;
    private String precision;
    private String description;
    private String unit;
    private String comprehension;


    public Abbreviation() {
    }

    public Abbreviation(String aggregation, String precision, String description, String unit) {
        this.aggregation = aggregation;
        this.precision = precision;
        this.description = description;
        this.unit = unit;
    }

    public Abbreviation(String aggregation, String precision, String description, String unit, String comprehension) {
        this.aggregation = aggregation;
        this.precision = precision;
        this.description = description;
        this.unit = unit;
        this.comprehension = comprehension;
    }

    @ModelProperty
    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    @ModelProperty
    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    @ModelProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ModelProperty
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @ModelProperty
    public String getComprehension() {
        return comprehension;
    }

    public void setComprehension(String comprehension) {
        this.comprehension = comprehension;
    }
}

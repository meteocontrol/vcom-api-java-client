package com.meteocontrol.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Abbreviation {
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

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getComprehension() {
        return comprehension;
    }

    public void setComprehension(String comprehension) {
        this.comprehension = comprehension;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Abbreviation))
            return false;
        Abbreviation p = (Abbreviation) obj;
        return p.getAggregation().equals(this.aggregation) &&
                p.getPrecision().equals(this.precision) &&
                p.getDescription().equals(this.description) &&
                p.getUnit().equals(this.unit);
    }
}

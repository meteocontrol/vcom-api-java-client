package com.meteocontrol.client.models.bulk;
import com.meteocontrol.client.writer.Writer;

public class BulkMeasurements {

    private String rawData =null;

    public BulkMeasurements() {
    }

    public BulkMeasurements(String response) {
        this.rawData = response;
    }

    public void setValue(String rawData) {
        this.rawData = rawData;
    }

    public String getAsString() {
        return this.rawData;
    }

    public void write(Writer writer) {
        writer.write(this.rawData);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BulkMeasurements))
            return false;
        BulkMeasurements p = (BulkMeasurements)obj;
        return p.getAsString().equals(this.rawData) ;
    }
}

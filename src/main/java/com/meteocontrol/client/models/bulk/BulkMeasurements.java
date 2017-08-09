package com.meteocontrol.client.models.bulk;
import com.meteocontrol.client.models.BaseModel;
import com.meteocontrol.client.models.annotation.ModelProperty;
import com.meteocontrol.client.writer.Writer;

public class BulkMeasurements extends BaseModel {

    private String rawData =null;

    public BulkMeasurements() {
    }

    public BulkMeasurements(String response) {
        this.rawData = response;
    }

    public void setValue(String rawData) {
        this.rawData = rawData;
    }

    @ModelProperty
    public String getAsString() {
        return this.rawData;
    }

    public void write(Writer writer) {
        writer.write(this.rawData);
    }
}

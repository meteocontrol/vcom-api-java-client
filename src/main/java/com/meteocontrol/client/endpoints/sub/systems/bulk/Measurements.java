package com.meteocontrol.client.endpoints.sub.systems.bulk;


import com.meteocontrol.client.filters.MeasurementsCriteria;
import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.models.bulk.BulkMeasurements;

import java.io.IOException;

public class Measurements extends SubEndpoint {

    public Measurements(EndpointInterface parent) {
        this.url = "/measurements";
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public BulkMeasurements get(MeasurementsCriteria criteria) {
        String systemJson = this.api.run(this.getUrl(), criteria.getAsList());
        return new BulkMeasurements(systemJson);
    }
}

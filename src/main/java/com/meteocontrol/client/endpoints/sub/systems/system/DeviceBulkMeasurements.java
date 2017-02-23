package com.meteocontrol.client.endpoints.sub.systems.system;

import com.meteocontrol.client.endpoints.EndpointInterface;
import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.filters.MeasurementsCriteria;
import com.meteocontrol.client.models.bulk.BulkMeasurements;

public class DeviceBulkMeasurements extends SubEndpoint {
    public DeviceBulkMeasurements(EndpointInterface parent) {
        this.url = "/measurements";
        this.parent = parent;
        this.api = parent.getApiClient();
    }
    public BulkMeasurements get(MeasurementsCriteria criteria) {
        String systemJson = this.api.run(this.getUrl(), criteria.getAsList());
        return new BulkMeasurements(systemJson);
    }
}

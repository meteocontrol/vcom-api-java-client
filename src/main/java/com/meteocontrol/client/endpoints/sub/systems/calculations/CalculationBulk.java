package com.meteocontrol.client.endpoints.sub.systems.calculations;

import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.EndpointInterface;

public class CalculationBulk extends SubEndpoint {
    public CalculationBulk(EndpointInterface parent) {
        this.url = "/bulk";
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public CalculationBulkMeasurements measurements() {
        return new CalculationBulkMeasurements(this);
    }
}

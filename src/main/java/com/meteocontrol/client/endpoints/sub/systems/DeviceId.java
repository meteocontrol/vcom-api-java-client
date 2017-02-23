package com.meteocontrol.client.endpoints.sub.systems;

import com.meteocontrol.client.endpoints.sub.SubEndpoint;
import com.meteocontrol.client.endpoints.EndpointInterface;
import org.apache.commons.lang3.StringUtils;

public class DeviceId extends SubEndpoint {
    public DeviceId(EndpointInterface parent, String idString) {
        this.url = "/" + idString;
        this.api = parent.getApiClient();
        this.parent = parent;
    }

    public DeviceId(EndpointInterface parent, String[] idStrings) {
        this(parent, StringUtils.join(idStrings, ","));
    }
}

package com.meteocontrol.client.endpoints.sub;

import com.meteocontrol.client.endpoints.EndpointInterface;
import org.apache.commons.lang3.StringUtils;

public class AbbreviationId extends SubEndpoint {
    public AbbreviationId(EndpointInterface parent, String id) {
        this.url = "/" + id;
        this.parent = parent;
        this.api = parent.getApiClient();
    }

    public AbbreviationId(EndpointInterface parent, String[] ids) {
        this(parent, StringUtils.join(ids, ","));
    }
}

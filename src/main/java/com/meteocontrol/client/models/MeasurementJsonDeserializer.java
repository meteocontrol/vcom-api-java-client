package com.meteocontrol.client.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.lang.*;
import java.util.*;

public class MeasurementJsonDeserializer extends JsonDeserializer<Measurement> {
    @Override
    public Measurement deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        ObjectMapper mapper = new ObjectMapper();
        Iterator<Map.Entry<String, JsonNode>> itr = node.fields();
        String systemKey = "";
        HashMap<String, MeasurementValue[]> values = new HashMap<String, MeasurementValue[]>();
        while (itr.hasNext()) {
            Map.Entry<String, JsonNode> ele = itr.next();
            String key = ele.getKey();
            if ("systemKey".equals(key)) {
                systemKey = ele.getValue().asText();
            } else {
                MeasurementValue[] measurementValues = mapper.readValue(ele.getValue().toString(), MeasurementValue[].class);
                values.put(ele.getKey(), measurementValues);
            }
        }

        return new Measurement(systemKey, values);
    }
}

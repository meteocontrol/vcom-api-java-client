package com.meteocontrol.client.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DeviceMeasurementJsonDeserializer extends JsonDeserializer<DeviceMeasurement> {
    @Override
    public DeviceMeasurement deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        ObjectMapper mapper = new ObjectMapper();
        Iterator<Map.Entry<String, JsonNode>> itr = node.fields();
        HashMap<String, AbbreviationMeasurements> values = new HashMap<String, AbbreviationMeasurements>();

        while (itr.hasNext()) {
            Map.Entry<String, JsonNode> ele = itr.next();
            AbbreviationMeasurements measurementValues = mapper.readValue(ele.getValue().toString(), AbbreviationMeasurements.class);
            values.put(ele.getKey(), measurementValues);
        }

        return new DeviceMeasurement(values);
    }
}

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

public class AbbreviationMeasurementsJsonDeserializer extends JsonDeserializer<AbbreviationMeasurements> {
    @Override
    public AbbreviationMeasurements deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        ObjectMapper mapper = new ObjectMapper();
        Iterator<Map.Entry<String, JsonNode>> itr = node.fields();
        HashMap<String, MeasurementValue[]> values = new HashMap<String, MeasurementValue[]>();

        while (itr.hasNext()) {
            Map.Entry<String, JsonNode> ele = itr.next();
            MeasurementValue[] measurementValues = mapper.readValue(ele.getValue().toString(), MeasurementValue[].class);
            values.put(ele.getKey(), measurementValues);
        }

        return new AbbreviationMeasurements(values);
    }
}

package com.meteocontrol.client.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TechnicalDataJsonDeserializer extends JsonDeserializer<TechnicalData> {

    private TechnicalDevice[] panels;
    private TechnicalDevice[] inverters;
    private String nominalPower = "";
    private String siteArea = "";

    @Override
    public TechnicalData deserialize(
            JsonParser p, DeserializationContext ctxt
    ) throws IOException {
        JsonNode node = p.readValueAsTree();

        ObjectMapper mapper = new ObjectMapper();
        Iterator<Map.Entry<String, JsonNode>> itr = node.fields();
        while (itr.hasNext()) {
            Map.Entry<String, JsonNode> ele = itr.next();
            String key = ele.getKey();
            switch (key) {
                case "nominalPower":
                    this.nominalPower = ele.getValue().asText();
                    break;
                case "siteArea":
                    this.siteArea = ele.getValue().asText();
                    break;
                case "panels":
                    this.panels = mapper.readValue(ele.getValue().toString(), TechnicalDevice[].class);
                    break;
                case "inverters":
                    this.inverters = mapper.readValue(ele.getValue().toString(), TechnicalDevice[].class);
                    break;
            }
        }
        return new TechnicalData(this.nominalPower, this.siteArea, this.panels, this.inverters);
    }
}

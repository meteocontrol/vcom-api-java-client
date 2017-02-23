package com.meteocontrol.client.params;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum TicketSeverity {
    NORMAL {
        @Override
        public String toString() {
            return "normal";
        }
    },
    HIGH {
        @Override
        public String toString() {
            return "high";
        }
    },
    CRITICAL {
        @Override
        public String toString() {
            return "critical";
        }
    };


    private static Map<String, TicketSeverity> namesMap = new HashMap<String, TicketSeverity>();
    static {
        namesMap.put("normal", NORMAL);
        namesMap.put("high", HIGH);
        namesMap.put("critical", CRITICAL);
    }

    @JsonCreator
    public static TicketSeverity forValue(String value) {
        return namesMap.get(value);
    }
}

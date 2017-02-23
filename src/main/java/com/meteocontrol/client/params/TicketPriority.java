package com.meteocontrol.client.params;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum TicketPriority {
    LOW {
        @Override
        public String toString() {
            return "low";
        }
    },
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
    URGENT {
        @Override
        public String toString() {
            return "urgent";
        }
    };


    private static Map<String, TicketPriority> namesMap = new HashMap<String, TicketPriority>();
    static {
        namesMap.put("low", LOW);
        namesMap.put("normal", NORMAL);
        namesMap.put("high", HIGH);
        namesMap.put("urgent", URGENT);
    }

    @JsonCreator
    public static TicketPriority forValue(String value) {
        return namesMap.get(value);
    }
}

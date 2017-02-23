package com.meteocontrol.client.params;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum TicketStatus {
    OPEN {
        @Override
        public String toString() {
            return "open";
        }
    },
    CLOSED {
        @Override
        public String toString() {
            return "closed";
        }
    },
    DELETED {
        @Override
        public String toString() {
            return "deleted";
        }
    },
    ASSIGNED {
        @Override
        public String toString() {
            return "assigned";
        }
    },
    INPROGRESS {
        @Override
        public String toString() {
            return "inProgress";
        }
    };


    private static Map<String, TicketStatus> namesMap = new HashMap<String, TicketStatus>();
    static {
        namesMap.put("open", OPEN);
        namesMap.put("closed", CLOSED);
        namesMap.put("deleted", DELETED);
        namesMap.put("assigned", ASSIGNED);
        namesMap.put("inProgress", INPROGRESS);
    }

    @JsonCreator
    public static TicketStatus forValue(String value) {
        return namesMap.get(value);
    }
}

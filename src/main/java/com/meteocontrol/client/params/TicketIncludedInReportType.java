package com.meteocontrol.client.params;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum TicketIncludedInReportType {
    NO {
        @Override
        public String toString() {
            return "no";
        }
    },
    DETAIL {
        @Override
        public String toString() {
            return "detail";
        }
    },
    SUMMARY {
        @Override
        public String toString() {
            return "summary";
        }
    };


    private static Map<String, TicketIncludedInReportType> namesMap = new HashMap<String, TicketIncludedInReportType>();
    static {
        namesMap.put("no", NO);
        namesMap.put("detail", DETAIL);
        namesMap.put("summary", SUMMARY);
    }

    @JsonCreator
    public static TicketIncludedInReportType forValue(String value) {
        return namesMap.get(value);
    }
}

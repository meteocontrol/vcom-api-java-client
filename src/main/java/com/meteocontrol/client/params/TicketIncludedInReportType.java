package com.meteocontrol.client.params;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum TicketIncludedInReportType implements CharSequence {
    NO {
        private String value = "no";
        @Override
        public int length() {
            return value.length();
        }

        @Override
        public char charAt(int i) {
            return value.charAt(i);
        }

        @Override
        public CharSequence subSequence(int i, int i1) {
            return value.subSequence(i, i1);
        }

        @Override
        public String toString() {
            return value;
        }
    },
    DETAIL {
        private String value = "detail";
        @Override
        public int length() {
            return value.length();
        }

        @Override
        public char charAt(int i) {
            return value.charAt(i);
        }

        @Override
        public CharSequence subSequence(int i, int i1) {
            return value.subSequence(i, i1);
        }

        @Override
        public String toString() {
            return value;
        }
    },
    SUMMARY {
        private String value = "summary";
        @Override
        public int length() {
            return value.length();
        }

        @Override
        public char charAt(int i) {
            return value.charAt(i);
        }

        @Override
        public CharSequence subSequence(int i, int i1) {
            return value.subSequence(i, i1);
        }

        @Override
        public String toString() {
            return value;
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

package com.meteocontrol.client.params;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum TicketSeverity implements CharSequence {
    NORMAL {
        private String value = "normal";
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
    HIGH {
        private String value = "high";
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
    CRITICAL {
        private String value = "critical";
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

package com.meteocontrol.client.params;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum TicketPriority implements CharSequence {
    LOW {
        private String value = "low";
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

    URGENT {
        private String value = "urgent";
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

package com.meteocontrol.client.params;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum TicketStatus implements CharSequence {
    OPEN {
        private String value = "open";
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
    CLOSED {
        private String value = "closed";
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
    DELETED {
        private String value = "deleted";
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
    ASSIGNED {
        private String value = "assigned";
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
    INPROGRESS {
        private String value = "inProgress";
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

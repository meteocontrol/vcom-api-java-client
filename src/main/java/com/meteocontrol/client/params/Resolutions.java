package com.meteocontrol.client.params;

public enum Resolutions {
    INTERVAL {
        @Override
        public String toString() {
            return "interval";
        }
    },
    DAY {
        @Override
        public String toString() {
            return "day";
        }
    },
    MONTH {
        @Override
        public String toString() {
            return "month";
        }
    },
    YEAR {
        @Override
        public String toString() {
            return "year";
        }
    }
}

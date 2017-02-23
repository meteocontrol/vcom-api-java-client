package com.meteocontrol.client.params.bulkCsv;


public enum CsvPrecision {
    PRECISION_0 {
        @Override
        public String toString() {
            return "0";
        }
    },
    PRECISION_1 {
        @Override
        public String toString() {
            return "1";
        }
    },
    PRECISION_2 {
        @Override
        public String toString() {
            return "2";
        }
    },
    PRECISION_3 {
        @Override
        public String toString() {
            return "3";
        }
    },
    PRECISION_4 {
        @Override
        public String toString() {
            return "4";
        }
    }
}

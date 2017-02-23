package com.meteocontrol.client.params;


public enum BulkResponseFormat {
    FORMAT_CSV {
        @Override
        public String toString() {
            return "csv";
        }
    },
    FORMAT_JSON {
        @Override
        public String toString() {
            return "json";
        }
    }
}

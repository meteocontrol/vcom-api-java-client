package com.meteocontrol.client.params.bulkCsv;


public enum CsvDecimalPoint {
    DECIMAL_POINT_SEMICOLON {
        @Override
        public String toString() {
            return "semicolon";
        }
    },
    DECIMAL_POINT_COLON {
        @Override
        public String toString() {
            return "colon";
        }
    },
    DECIMAL_POINT_COMMA {
        @Override
        public String toString() {
            return "comma";
        }
    },
    DECIMAL_POINT_DOT {
        @Override
        public String toString() {
            return "dot";
        }
    }
}

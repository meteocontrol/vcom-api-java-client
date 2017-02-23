package com.meteocontrol.client.params.bulkCsv;


public enum CsvDelimiter {
    DELIMITER_COMMA {
        @Override
        public String toString() {
            return "comma";
        }
    },
    DELIMITER_SEMICOLON {
        @Override
        public String toString() {
            return "semicolon";
        }
    },
    DELIMITER_COLON {
        @Override
        public String toString() {
            return "colon";
        }
    },
    DELIMITER_TAB {
        @Override
        public String toString() {
            return "tab";
        }
    }
}

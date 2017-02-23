package com.meteocontrol.client.params.bulkCsv;


public enum CsvLineBreak {
    LINE_BREAK_LF {
        @Override
        public String toString() {
            return "LF";
        }
    },
    LINE_BREAK_CR {
        @Override
        public String toString() {
            return "CR";
        }
    },
    LINE_BREAK_CRLF {
        @Override
        public String toString() {
            return "CR/LF";
        }
    }
}

package com.meteocontrol.client.params;


public enum AuthorizationMode {
    BASIC {
        @Override
        public String toString() {
            return "basic";
        }
    },
    OAUTH {
        @Override
        public String toString() {
            return "oauth";
        }
    }
}

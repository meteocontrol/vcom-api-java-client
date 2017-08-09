package com.meteocontrol.client.exceptions;

public class ApiClientException extends RuntimeException {
    private int code;

    public ApiClientException(String message) {
        super(message);
    }

    public ApiClientException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}

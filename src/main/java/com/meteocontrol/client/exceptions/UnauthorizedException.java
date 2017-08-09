package com.meteocontrol.client.exceptions;

public class UnauthorizedException extends RuntimeException {
    private int code;

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
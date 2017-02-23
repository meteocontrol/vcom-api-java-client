package com.meteocontrol.client.exceptions;

public class ApiClientException extends RuntimeException {
    public ApiClientException(String message) {
        super(message);
    }
}

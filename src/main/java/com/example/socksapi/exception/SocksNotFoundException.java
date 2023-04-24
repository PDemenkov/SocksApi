package com.example.socksapi.exception;

public class SocksNotFoundException extends RuntimeException {

    public SocksNotFoundException() {
    }

    public SocksNotFoundException(String message) {
        super(message);
    }
}

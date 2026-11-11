package com.test1.gestione_ordini.exception;

public class ExternalUserNotFoundException extends RuntimeException {
    public ExternalUserNotFoundException(String message) {
        super(message);
    }
}

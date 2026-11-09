package com.test1.gestione_ordini.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente con id " + id + " non trovato");
    }
}

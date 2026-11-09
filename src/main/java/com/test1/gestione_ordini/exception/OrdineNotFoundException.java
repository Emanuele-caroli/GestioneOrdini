package com.test1.gestione_ordini.exception;

public class OrdineNotFoundException extends RuntimeException {

    public OrdineNotFoundException(Long id) {
        super("Ordine con id " + id + " non trovato");
    }
}

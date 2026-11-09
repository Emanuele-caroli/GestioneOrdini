package com.test1.gestione_ordini.entity.enums;

public enum Stato {

    RICEVUTO,
    IN_LAVORAZIONE,
    SPEDITO,
    ANNULLATO;

    public boolean puoPassareA(Stato nuovoStato) {
        return switch (this) {
            case RICEVUTO -> nuovoStato == IN_LAVORAZIONE || nuovoStato == ANNULLATO;
            case IN_LAVORAZIONE -> nuovoStato == SPEDITO || nuovoStato == ANNULLATO;
            case SPEDITO, ANNULLATO -> false;
        };
    }
}

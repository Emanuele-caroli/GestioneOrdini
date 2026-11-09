package com.test1.gestione_ordini.exception;

import com.test1.gestione_ordini.entity.enums.Stato;

public class TransizioneStatoNonConsentitaException extends RuntimeException {

    public TransizioneStatoNonConsentitaException(Stato statoAttuale, Stato nuovoStato) {
        super("Transizione da " + statoAttuale + " a " + nuovoStato + " non consentita");
    }
}

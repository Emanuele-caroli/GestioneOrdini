package com.test1.gestione_ordini.repository.projection;

import com.test1.gestione_ordini.entity.enums.Stato;

public interface StatisticaStatoProjection {

    Stato getStato();

    Long getNumeroOrdini();
}

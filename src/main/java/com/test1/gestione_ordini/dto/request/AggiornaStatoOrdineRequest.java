package com.test1.gestione_ordini.dto.request;

import com.test1.gestione_ordini.entity.enums.Stato;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Nuovo stato da assegnare all'ordine")
public class AggiornaStatoOrdineRequest {

    @Schema(description = "Nuovo stato richiesto: IN_LAVORAZIONE, SPEDITO o ANNULLATO. "
            + "Transizioni ammesse: RICEVUTO → IN_LAVORAZIONE o ANNULLATO; "
            + "IN_LAVORAZIONE → SPEDITO o ANNULLATO. SPEDITO e ANNULLATO sono stati finali.",
            example = "IN_LAVORAZIONE")
    @NotNull
    private Stato stato;
}

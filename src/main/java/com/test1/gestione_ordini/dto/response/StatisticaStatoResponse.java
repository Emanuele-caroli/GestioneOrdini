package com.test1.gestione_ordini.dto.response;

import com.test1.gestione_ordini.entity.enums.Stato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@Schema(description = "Numero di ordini presenti in uno stato")
public class StatisticaStatoResponse {

    @Schema(description = "Stato dell'ordine", example = "RICEVUTO", requiredMode = REQUIRED)
    private Stato stato;

    @Schema(description = "Numero di ordini in quello stato", example = "4", requiredMode = REQUIRED)
    private Long numeroOrdini;
}

package com.test1.gestione_ordini.dto.response;

import com.test1.gestione_ordini.entity.enums.Stato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@Schema(description = "Ordine restituito da creazione, dettaglio, elenco e aggiornamento stato")
public class OrdineResponse {

    @Schema(description = "Identificativo generato dal database", example = "1", requiredMode = REQUIRED)
    private Long id;

    @Schema(description = "Codice univoco generato dall'applicazione",
            example = "ORD-20261106-A1B2C3D4E5F6", requiredMode = REQUIRED)
    private String codiceOrdine;

    @Schema(description = "Data e ora di creazione", example = "2026-11-06T10:15:30", requiredMode = REQUIRED)
    private LocalDateTime dataOrdine;

    @Schema(description = "Nome del cliente", example = "Mario", requiredMode = REQUIRED)
    private String nomeCliente;

    @Schema(description = "Cognome del cliente", example = "Rossi", requiredMode = REQUIRED)
    private String cognomeCliente;

    @Schema(description = "Email di contatto", example = "mario.rossi@example.com", requiredMode = REQUIRED)
    private String emailCliente;

    @Schema(description = "Telefono del cliente; null se non indicato", example = "+39 333 1234567")
    private String telefonoCliente;

    @Schema(description = "Indirizzo di spedizione", example = "Via Roma 10", requiredMode = REQUIRED)
    private String indirizzoSpedizione;

    @Schema(description = "Città di spedizione", example = "Milano", requiredMode = REQUIRED)
    private String cittaSpedizione;

    @Schema(description = "CAP", example = "20121", requiredMode = REQUIRED)
    private String cap;

    @Schema(description = "Prodotto ordinato", example = "Tastiera meccanica", requiredMode = REQUIRED)
    private String prodotto;

    @Schema(description = "Quantità ordinata", example = "2", requiredMode = REQUIRED)
    private Integer quantita;

    @Schema(description = "Prezzo unitario in euro", example = "49.90", requiredMode = REQUIRED)
    private Double prezzoUnitario;

    @Schema(description = "Totale calcolato dal sistema: prezzoUnitario × quantita",
            example = "99.80", requiredMode = REQUIRED)
    private Double totale;

    @Schema(description = "Stato dell'ordine; alla creazione vale RICEVUTO",
            example = "RICEVUTO", requiredMode = REQUIRED)
    private Stato stato;
}

package com.test1.gestione_ordini.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Dati per registrare un nuovo ordine. id, codiceOrdine, dataOrdine, totale e stato "
        + "sono generati dal sistema e non vanno inviati.")
public class OrdineRequest {

    @Schema(description = "Nome del cliente", example = "Mario")
    @NotBlank
    @Size(min = 2, max = 50)
    private String nomeCliente;

    @Schema(description = "Cognome del cliente", example = "Rossi")
    @NotBlank
    @Size(min = 2, max = 50)
    private String cognomeCliente;

    @Schema(description = "Email di contatto", example = "mario.rossi@example.com")
    @NotBlank
    @Email
    @Size(max = 100)
    private String emailCliente;

    @Schema(description = "Telefono del cliente (facoltativo)", example = "+39 333 1234567")
    @Size(max = 20)
    private String telefonoCliente;

    @Schema(description = "Indirizzo di spedizione completo", example = "Via Roma 10")
    @NotBlank
    @Size(min = 5, max = 120)
    private String indirizzoSpedizione;

    @Schema(description = "Città di spedizione", example = "Milano")
    @NotBlank
    @Size(min = 2, max = 50)
    private String cittaSpedizione;

    @Schema(description = "CAP italiano di 5 cifre", example = "20121")
    @NotBlank
    @Pattern(regexp = "\\d{5}", message = "deve essere composto da esattamente 5 cifre")
    private String cap;

    @Schema(description = "Nome o descrizione del prodotto", example = "Tastiera meccanica")
    @NotBlank
    @Size(min = 2, max = 80)
    private String prodotto;

    @Schema(description = "Quantità ordinata, da 1 a 999", example = "2")
    @NotNull
    @Min(1)
    @Max(999)
    private Integer quantita;

    @Schema(description = "Prezzo unitario in euro, maggiore di zero", example = "49.90")
    @NotNull
    @Positive
    private Double prezzoUnitario;
}

package com.test1.gestione_ordini.controller;

import com.test1.gestione_ordini.dto.request.AggiornaStatoOrdineRequest;
import com.test1.gestione_ordini.dto.request.OrdineRequest;
import com.test1.gestione_ordini.dto.response.OrdineResponse;
import com.test1.gestione_ordini.dto.response.StatisticaStatoResponse;
import com.test1.gestione_ordini.entity.enums.Stato;
import com.test1.gestione_ordini.service.OrdineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ordini")
@RequiredArgsConstructor
@Tag(name = "Ordini", description = "Registrazione, consultazione e aggiornamento dello stato degli ordini")
@ApiResponse(responseCode = "500", description = "Errore inatteso: risposta generica, senza dettagli tecnici",
        content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                schema = @Schema(implementation = ProblemDetail.class)))
public class OrdiniController {

    private final OrdineService ordineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra un nuovo ordine",
            description = "Valida i dati, calcola totale = prezzoUnitario × quantita, genera codice e data "
                    + "e imposta lo stato iniziale RICEVUTO. Non inviare id, codiceOrdine, dataOrdine, "
                    + "totale e stato: li genera il sistema.")
    @ApiResponse(responseCode = "201", description = "Ordine registrato")
    @ApiResponse(responseCode = "400",
            description = "Dati non validi: la lista \"errori\" indica, per ogni campo, il vincolo violato",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(value = """
                            {
                              "type": "about:blank",
                              "title": "Bad Request",
                              "status": 400,
                              "detail": "Dati di input non validi",
                              "instance": "/api/ordini",
                              "errori": [
                                "cap: deve essere composto da esattamente 5 cifre",
                                "quantita: deve essere minore o uguale a 999"
                              ]
                            }""")))
    public OrdineResponse creaOrdine(@Valid @RequestBody OrdineRequest request) {
        return ordineService.creaOrdine(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Dettaglio di un ordine",
            description = "Restituisce l'ordine con l'identificativo indicato.")
    @ApiResponse(responseCode = "200", description = "Ordine trovato")
    @ApiResponse(responseCode = "400", description = "id non numerico",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "404", description = "Nessun ordine con l'id indicato, oppure id minore o uguale a 0",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)))
    public OrdineResponse dettaglioOrdine(
            @Parameter(description = "Identificativo dell'ordine, maggiore di 0", example = "1")
            @PathVariable Long id) {
        return ordineService.dettaglioOrdine(id);
    }

    @GetMapping
    @Operation(summary = "Elenco degli ordini",
            description = "Restituisce tutti gli ordini oppure, se indicato il filtro, solo quelli nello stato "
                    + "richiesto. Senza risultati restituisce una lista vuota.")
    @ApiResponse(responseCode = "200", description = "Lista degli ordini, eventualmente vuota")
    @ApiResponse(responseCode = "400", description = "Valore di stato non ammesso",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)))
    public PagedModel<OrdineResponse> elencoOrdini(
            @RequestParam(required = false) Stato stato,
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return new PagedModel<>(ordineService.elencoOrdini(stato, pageable));
    }

    @PatchMapping("/{id}/stato")
    @Operation(summary = "Aggiorna lo stato di un ordine",
            description = "Transizioni ammesse: RICEVUTO → IN_LAVORAZIONE o ANNULLATO; "
                    + "IN_LAVORAZIONE → SPEDITO o ANNULLATO. SPEDITO e ANNULLATO sono stati finali.")
    @ApiResponse(responseCode = "200", description = "Stato aggiornato")
    @ApiResponse(responseCode = "400", description = "id non numerico, oppure stato mancante o inesistente",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "404", description = "Nessun ordine con l'id indicato, oppure id minore o uguale a 0",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "409", description = "Transizione di stato non consentita",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)))
    public OrdineResponse aggiornaStato(
            @Parameter(description = "Identificativo dell'ordine, maggiore di 0", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody AggiornaStatoOrdineRequest request) {
        return ordineService.aggiornaStato(id, request);
    }

    @GetMapping("/statistiche/stato")
    @Operation(summary = "Numero di ordini per stato",
            description = "Conta gli ordini raggruppandoli per stato (COUNT e GROUP BY sulla sola tabella ORDINE). "
                    + "Gli stati senza ordini non compaiono.")
    @ApiResponse(responseCode = "200", description = "Lista di oggetti {stato, numeroOrdini}")
    public List<StatisticaStatoResponse> statistichePerStato() {
        return ordineService.statistichePerStato();
    }
}

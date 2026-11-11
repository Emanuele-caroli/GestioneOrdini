package com.test1.gestione_ordini.controller;

import com.test1.gestione_ordini.dto.response.OrdineResponse;
import com.test1.gestione_ordini.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/clienti")
@RequiredArgsConstructor
@Tag(name = "Clienti", description = "Consultazione dei clienti e dei loro ordini")
public class ClientiController {

    private final ClienteService clienteService;

    @GetMapping("/{id}/ordini")
    @Operation(summary = "Ordini di un cliente",
                description = "Restituisce tutti gli ordinidi un cliente")
    @ApiResponse(responseCode = "200", description = "Lista degli ordini eventualmente")
    public List<OrdineResponse> ordiniDelCliente(
            @Parameter(description = "Identificativo del cliente", example = "1") Long id) {
        return clienteService.ordiniDelCliente(id);
    }
}

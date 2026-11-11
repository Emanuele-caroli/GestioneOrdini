package com.test1.gestione_ordini.client;

import com.test1.gestione_ordini.dto.response.ExternalUserResponse;
import com.test1.gestione_ordini.exception.ExternalServiceException;
import com.test1.gestione_ordini.exception.ExternalUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class ExternalUserClient {

    private final RestClient restClient;

    public ExternalUserResponse findUser(Long id) {
        try {
            return restClient.get()
                    .uri("/users/{id}", id)
                    .retrieve()
                    .body(ExternalUserResponse.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ExternalUserNotFoundException("Utente con ID " + id + " non trovato nel sistema esterno");
        } catch (RestClientException ex) {
            throw new ExternalServiceException("Errore di comunicazione con il servizio utenti. Dettaglio: " + ex.getMessage());
        }
    }

}

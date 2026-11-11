package com.test1.gestione_ordini.service;

import com.test1.gestione_ordini.client.ExternalUserClient;
import com.test1.gestione_ordini.dto.response.ExternalUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalUserService {

    private final ExternalUserClient externalUserClient;

    public ExternalUserResponse findUser(Long id) {
        return externalUserClient.findUser(id);
    }

}

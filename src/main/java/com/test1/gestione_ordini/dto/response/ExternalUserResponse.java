package com.test1.gestione_ordini.dto.response;

import lombok.Data;

@Data
public class ExternalUserResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
}

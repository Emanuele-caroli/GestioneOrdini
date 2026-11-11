package com.test1.gestione_ordini.controller;

import com.test1.gestione_ordini.dto.response.ExternalUserResponse;
import com.test1.gestione_ordini.service.ExternalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external-users")
@RequiredArgsConstructor
public class ExternalUserController {

    private final ExternalUserService externalUserService;

    @GetMapping("/{id}")
    public ExternalUserResponse findUser(@PathVariable Long id) {
        return externalUserService.findUser(id);
    }

}

package com.test1.gestione_ordini.service;

import com.test1.gestione_ordini.dto.response.OrdineResponse;
import com.test1.gestione_ordini.entity.ClienteEntity;
import com.test1.gestione_ordini.exception.ClienteNotFoundException;
import com.test1.gestione_ordini.mapper.OrdineMapper;
import com.test1.gestione_ordini.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final OrdineMapper ordineMapper;

    public List<OrdineResponse> ordiniDelCliente(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return ordineMapper.toResponseList(cliente.getOrdini());
    }

}

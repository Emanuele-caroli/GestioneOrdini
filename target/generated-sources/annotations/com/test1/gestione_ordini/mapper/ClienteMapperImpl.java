package com.test1.gestione_ordini.mapper;

import com.test1.gestione_ordini.dto.request.OrdineRequest;
import com.test1.gestione_ordini.entity.ClienteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-09T11:53:43+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.4.1 (Eclipse Adoptium)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteEntity toCliente(OrdineRequest request) {
        if ( request == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome( request.getNomeCliente() );
        clienteEntity.setCognome( request.getCognomeCliente() );
        clienteEntity.setEmail( request.getEmailCliente() );
        clienteEntity.setTelefono( request.getTelefonoCliente() );

        return clienteEntity;
    }
}

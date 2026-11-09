package com.test1.gestione_ordini.mapper;

import com.test1.gestione_ordini.dto.request.OrdineRequest;
import com.test1.gestione_ordini.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClienteMapper {

    @Mapping(target = "nome", source = "nomeCliente")
    @Mapping(target = "cognome", source = "cognomeCliente")
    @Mapping(target = "email", source = "emailCliente")
    @Mapping(target = "telefono", source = "telefonoCliente")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ordini", ignore = true)
    ClienteEntity toCliente(OrdineRequest request);
}

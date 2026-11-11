package com.test1.gestione_ordini.mapper;

import com.test1.gestione_ordini.dto.request.OrdineRequest;
import com.test1.gestione_ordini.dto.response.OrdineResponse;
import com.test1.gestione_ordini.dto.response.StatisticaStatoResponse;
import com.test1.gestione_ordini.entity.OrdineEntity;
import com.test1.gestione_ordini.repository.projection.StatisticaStatoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrdineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codiceOrdine", ignore = true)
    @Mapping(target = "dataOrdine", ignore = true)
    @Mapping(target = "totale", ignore = true)
    @Mapping(target = "stato", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    OrdineEntity toEntity(OrdineRequest request);

    @Mapping(target = "nomeCliente", source = "cliente.nome")
    @Mapping(target = "cognomeCliente", source = "cliente.cognome")
    @Mapping(target = "emailCliente", source = "cliente.email")
    @Mapping(target = "telefonoCliente", source = "cliente.telefono")
    OrdineResponse toResponse(OrdineEntity entity);

    List<OrdineResponse> toResponseList(List<OrdineEntity> entities);

    StatisticaStatoResponse toStatisticaResponse(StatisticaStatoProjection projection);

    List<StatisticaStatoResponse> toStatisticaResponseList(List<StatisticaStatoProjection> projections);
}

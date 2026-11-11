package com.test1.gestione_ordini.mapper;

import com.test1.gestione_ordini.dto.request.OrdineRequest;
import com.test1.gestione_ordini.dto.response.OrdineResponse;
import com.test1.gestione_ordini.dto.response.StatisticaStatoResponse;
import com.test1.gestione_ordini.entity.ClienteEntity;
import com.test1.gestione_ordini.entity.OrdineEntity;
import com.test1.gestione_ordini.repository.projection.StatisticaStatoProjection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-09T11:53:43+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.4.1 (Eclipse Adoptium)"
)
@Component
public class OrdineMapperImpl implements OrdineMapper {

    @Override
    public OrdineEntity toEntity(OrdineRequest request) {
        if ( request == null ) {
            return null;
        }

        OrdineEntity ordineEntity = new OrdineEntity();

        ordineEntity.setIndirizzoSpedizione( request.getIndirizzoSpedizione() );
        ordineEntity.setCittaSpedizione( request.getCittaSpedizione() );
        ordineEntity.setCap( request.getCap() );
        ordineEntity.setProdotto( request.getProdotto() );
        ordineEntity.setQuantita( request.getQuantita() );
        ordineEntity.setPrezzoUnitario( request.getPrezzoUnitario() );

        return ordineEntity;
    }

    @Override
    public OrdineResponse toResponse(OrdineEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OrdineResponse ordineResponse = new OrdineResponse();

        ordineResponse.setNomeCliente( entityClienteNome( entity ) );
        ordineResponse.setCognomeCliente( entityClienteCognome( entity ) );
        ordineResponse.setEmailCliente( entityClienteEmail( entity ) );
        ordineResponse.setTelefonoCliente( entityClienteTelefono( entity ) );
        ordineResponse.setId( entity.getId() );
        ordineResponse.setCodiceOrdine( entity.getCodiceOrdine() );
        ordineResponse.setDataOrdine( entity.getDataOrdine() );
        ordineResponse.setIndirizzoSpedizione( entity.getIndirizzoSpedizione() );
        ordineResponse.setCittaSpedizione( entity.getCittaSpedizione() );
        ordineResponse.setCap( entity.getCap() );
        ordineResponse.setProdotto( entity.getProdotto() );
        ordineResponse.setQuantita( entity.getQuantita() );
        ordineResponse.setPrezzoUnitario( entity.getPrezzoUnitario() );
        ordineResponse.setTotale( entity.getTotale() );
        ordineResponse.setStato( entity.getStato() );

        return ordineResponse;
    }

    @Override
    public List<OrdineResponse> toResponseList(List<OrdineEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OrdineResponse> list = new ArrayList<OrdineResponse>( entities.size() );
        for ( OrdineEntity ordineEntity : entities ) {
            list.add( toResponse( ordineEntity ) );
        }

        return list;
    }

    @Override
    public StatisticaStatoResponse toStatisticaResponse(StatisticaStatoProjection projection) {
        if ( projection == null ) {
            return null;
        }

        StatisticaStatoResponse statisticaStatoResponse = new StatisticaStatoResponse();

        statisticaStatoResponse.setStato( projection.getStato() );
        statisticaStatoResponse.setNumeroOrdini( projection.getNumeroOrdini() );

        return statisticaStatoResponse;
    }

    @Override
    public List<StatisticaStatoResponse> toStatisticaResponseList(List<StatisticaStatoProjection> projections) {
        if ( projections == null ) {
            return null;
        }

        List<StatisticaStatoResponse> list = new ArrayList<StatisticaStatoResponse>( projections.size() );
        for ( StatisticaStatoProjection statisticaStatoProjection : projections ) {
            list.add( toStatisticaResponse( statisticaStatoProjection ) );
        }

        return list;
    }

    private String entityClienteNome(OrdineEntity ordineEntity) {
        ClienteEntity cliente = ordineEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getNome();
    }

    private String entityClienteCognome(OrdineEntity ordineEntity) {
        ClienteEntity cliente = ordineEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getCognome();
    }

    private String entityClienteEmail(OrdineEntity ordineEntity) {
        ClienteEntity cliente = ordineEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getEmail();
    }

    private String entityClienteTelefono(OrdineEntity ordineEntity) {
        ClienteEntity cliente = ordineEntity.getCliente();
        if ( cliente == null ) {
            return null;
        }
        return cliente.getTelefono();
    }
}

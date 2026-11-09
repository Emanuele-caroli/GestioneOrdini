package com.test1.gestione_ordini.repository;

import com.test1.gestione_ordini.entity.OrdineEntity;
import com.test1.gestione_ordini.entity.enums.Stato;
import com.test1.gestione_ordini.repository.projection.StatisticaStatoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdineRepository extends JpaRepository<OrdineEntity, Long> {

    @EntityGraph(attributePaths = "cliente")
    Page<OrdineEntity> findByStato(Stato stato, Pageable pageable);

    @Query("""
            select o.stato as stato, count(o) as numeroOrdini
            from OrdineEntity o
            group by o.stato
            order by o.stato
            """)
    List<StatisticaStatoProjection> contaOrdiniPerStato();

    @Override
    @EntityGraph(attributePaths = "cliente")
    Page<OrdineEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "cliente")
    Optional<OrdineEntity> findById(Long id);
}

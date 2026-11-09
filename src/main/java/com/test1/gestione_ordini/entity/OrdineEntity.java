package com.test1.gestione_ordini.entity;

import com.test1.gestione_ordini.entity.enums.Stato;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordine")
@Getter
@Setter
@NoArgsConstructor
public class OrdineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false, length = 30)
    private String codiceOrdine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @Column(nullable = false, length = 120)
    private String indirizzoSpedizione;

    @Column(nullable = false, length = 50)
    private String cittaSpedizione;

    @Column(nullable = false, length = 5)
    private String cap;

    @Column(nullable = false, length = 80)
    private String prodotto;

    @Column(nullable = false)
    private Integer quantita;

    @Column(nullable = false)
    private Double prezzoUnitario;

    @Column(nullable = false)
    private Double totale;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Stato stato;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataOrdine;

}

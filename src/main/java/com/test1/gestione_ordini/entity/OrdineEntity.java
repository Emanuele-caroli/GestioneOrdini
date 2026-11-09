package com.test1.gestione_ordini.entity;

import com.test1.gestione_ordini.entity.enums.Stato;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(nullable = false, length = 50)
    private String nomeCliente;

    @Column(nullable = false, length = 50)
    private String cognomeCliente;

    @Column(nullable = false, length = 100)
    private String emailCliente;

    @Column(length = 20)
    private String telefonoCliente;

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

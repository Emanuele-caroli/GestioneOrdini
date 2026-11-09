package com.test1.gestione_ordini.service;

import com.test1.gestione_ordini.dto.request.AggiornaStatoOrdineRequest;
import com.test1.gestione_ordini.dto.request.OrdineRequest;
import com.test1.gestione_ordini.dto.response.OrdineResponse;
import com.test1.gestione_ordini.dto.response.StatisticaStatoResponse;
import com.test1.gestione_ordini.entity.OrdineEntity;
import com.test1.gestione_ordini.entity.enums.Stato;
import com.test1.gestione_ordini.exception.OrdineNotFoundException;
import com.test1.gestione_ordini.exception.TransizioneStatoNonConsentitaException;
import com.test1.gestione_ordini.mapper.OrdineMapper;
import com.test1.gestione_ordini.repository.OrdineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrdineService {

    private static final DateTimeFormatter FORMATO_DATA_CODICE = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final OrdineRepository ordineRepository;
    private final OrdineMapper ordineMapper;

    @Transactional
    public OrdineResponse creaOrdine(OrdineRequest request) {
        LocalDateTime dataOrdine = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        OrdineEntity ordine = ordineMapper.toEntity(request);
        ordine.setTotale(calcolaTotale(request.getPrezzoUnitario(), request.getQuantita()));
        ordine.setCodiceOrdine(generaCodiceOrdine(dataOrdine));
        ordine.setDataOrdine(dataOrdine);
        ordine.setStato(Stato.RICEVUTO);

        return ordineMapper.toResponse(ordineRepository.save(ordine));
    }

    public OrdineResponse dettaglioOrdine(Long id) {
        return ordineMapper.toResponse(trovaOrdine(id));
    }

    public List<OrdineResponse> elencoOrdini(Stato stato) {
        List<OrdineEntity> ordini = (stato == null)
                ? ordineRepository.findAll()
                : ordineRepository.findByStato(stato);
        return ordineMapper.toResponseList(ordini);
    }

    @Transactional
    public OrdineResponse aggiornaStato(Long id, AggiornaStatoOrdineRequest request) {
        OrdineEntity ordine = trovaOrdine(id);
        Stato nuovoStato = request.getStato();

        if (!ordine.getStato().puoPassareA(nuovoStato)) {
            throw new TransizioneStatoNonConsentitaException(ordine.getStato(), nuovoStato);
        }

        ordine.setStato(nuovoStato);
        return ordineMapper.toResponse(ordineRepository.save(ordine));
    }

    public List<StatisticaStatoResponse> statistichePerStato() {
        return ordineMapper.toStatisticaResponseList(ordineRepository.contaOrdiniPerStato());
    }

    private OrdineEntity trovaOrdine(Long id) {
        if (id <= 0) {
            throw new OrdineNotFoundException(id);
        }
        return ordineRepository.findById(id)
                .orElseThrow(() -> new OrdineNotFoundException(id));
    }

    private static Double calcolaTotale(Double prezzoUnitario, Integer quantita) {
        return Math.round(prezzoUnitario * quantita * 100) / 100.0;
    }

    private static String generaCodiceOrdine(LocalDateTime dataOrdine) {
        String parteCasuale = UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12)
                .toUpperCase(Locale.ROOT);
        return "ORD-" + dataOrdine.format(FORMATO_DATA_CODICE) + "-" + parteCasuale;
    }
}

package com.test1.gestione_ordini.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

public record ProductResponse(
        String id,

        String name,

        String description,

        BigDecimal price,

        String category,

        Instant createdAt,

        String brand,

        Map<String, Object> technicalDetails
) {
}

package com.test1.gestione_ordini.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Data
@Document(collection = "products")
public class ProductDocument {

    @Id
    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private String category;

    private Instant createdAt;

    private String brand;

    private Map<String, Object> technicalDetails;

    public ProductDocument() {
    }

}

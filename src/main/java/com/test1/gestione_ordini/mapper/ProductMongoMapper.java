package com.test1.gestione_ordini.mapper;

import com.test1.gestione_ordini.dto.request.ProductRequest;
import com.test1.gestione_ordini.dto.response.ProductResponse;
import com.test1.gestione_ordini.entity.ProductDocument;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProductMongoMapper {

    public ProductDocument toDocument(ProductRequest request) {

        ProductDocument document = new ProductDocument();

        document.setName(request.name());
        document.setDescription(request.description());
        document.setPrice(request.price());
        document.setCategory(request.category());
        document.setCreatedAt(Instant.now());
        document.setBrand(request.brand());
        document.setTechnicalDetails(request.technicalDetails());

        return document;
    }

    public ProductResponse toResponse(ProductDocument document) {

        return new ProductResponse(
                document.getId(),
                document.getName(),
                document.getDescription(),
                document.getPrice(),
                document.getCategory(),
                document.getCreatedAt(),
                document.getBrand(),
                document.getTechnicalDetails()
        );
    }
}
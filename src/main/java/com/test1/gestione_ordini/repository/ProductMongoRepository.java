package com.test1.gestione_ordini.repository;

import com.test1.gestione_ordini.entity.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductMongoRepository extends MongoRepository<ProductDocument, String> {

    List<ProductDocument> findByCategory(String category);

    List<ProductDocument> findByNameContainingIgnoreCase(String name);

}

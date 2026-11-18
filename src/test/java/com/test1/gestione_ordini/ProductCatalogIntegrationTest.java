package com.test1.gestione_ordini;

import com.test1.gestione_ordini.dto.request.ProductRequest;
import com.test1.gestione_ordini.entity.ProductDocument;
import com.test1.gestione_ordini.repository.ProductMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestRestTemplate non servono, dato che arrivano dalla classe che estendiamo (AbstractIntegrationTest)
@ActiveProfiles("mongo")
public class ProductCatalogIntegrationTest extends AbstractIntegrationTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductMongoRepository mongoRepository;

    @BeforeEach
    void setUp() {
        mongoRepository.deleteAll();
    }

    @Test
    void testCreateProduct() {
        ProductRequest request = new ProductRequest(
                "Lenovo ThinkPad",
                "Notebook professionale",
                new BigDecimal("1299.99"),
                "NOTEBOOK",
                "Lenovo",
                Map.of(
                        "processor", "Intel i7",
                        "ram", 32,
                        "storage", "1TB SSD",
                        "screenSize", 15.6,
                        "touchscreen",
                        false
                )
        );

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/catalog/products",
                request,
                String.class
        );

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("Lenovo ThinkPad"));

        ProductDocument product = mongoRepository.findAll()
                .stream()
                .filter(p -> p.getName().equals("Lenovo ThinkPad"))
                .findFirst()
                .orElseThrow();

        assertNotNull(product.getId());
        assertEquals("Lenovo ThinkPad", product.getName());

    }

}

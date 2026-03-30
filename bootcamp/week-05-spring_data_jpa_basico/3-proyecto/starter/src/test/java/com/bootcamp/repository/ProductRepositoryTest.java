package com.bootcamp.repository;

import com.bootcamp.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired TestEntityManager em;
    @Autowired ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        em.persist(new Product("Laptop Pro",     "A laptop",     new BigDecimal("999.99"),  5,  "Electronics"));
        em.persist(new Product("Wireless Mouse", "A mouse",      new BigDecimal("39.99"),   50, "Electronics"));
        em.persist(new Product("USB-C Hub",      "A hub",        new BigDecimal("49.99"),   20, "Electronics"));
        em.persist(new Product("Desk Chair",     "A chair",      new BigDecimal("249.99"),  3,  "Furniture"));
        em.persist(new Product("Standing Desk",  "A desk",       new BigDecimal("599.99"),  2,  "Furniture"));
        em.flush();
    }

    @Test
    void findByCategory_shouldReturnOnlyMatchingProducts() {
        var pageable = PageRequest.of(0, 10);
        var electronics = productRepository.findByCategory("Electronics", pageable);

        assertThat(electronics.getContent()).hasSize(3);
        assertThat(electronics.getContent())
                .extracting(Product::getCategory)
                .containsOnly("Electronics");
    }

    @Test
    void findByCategory_shouldSupportPagination() {
        var firstPage = productRepository.findByCategory("Electronics", PageRequest.of(0, 2));
        var secondPage = productRepository.findByCategory("Electronics", PageRequest.of(1, 2));

        assertThat(firstPage.getContent()).hasSize(2);
        assertThat(secondPage.getContent()).hasSize(1);
        assertThat(firstPage.getTotalElements()).isEqualTo(3);
    }

    @Test
    void searchByName_shouldFindPartialCaseInsensitiveMatch() {
        var results = productRepository.searchByName("lap");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Laptop Pro");
    }

    @Test
    void findByPriceBetween_shouldReturnProductsInRange() {
        var results = productRepository.findByPriceBetween(
                new BigDecimal("40.00"), new BigDecimal("300.00"));

        assertThat(results)
                .extracting(p -> p.getPrice().compareTo(new BigDecimal("40.00")) >= 0)
                .containsOnly(true);
    }

    @Test
    void decrementStock_shouldUpdateStockWhenSufficient() {
        var product = em.persist(new Product("Test Item", "desc", new BigDecimal("10.00"), 15, "Test"));
        em.flush();

        int updated = productRepository.decrementStock(product.getId(), 5);

        em.refresh(product);
        assertThat(updated).isEqualTo(1);
        assertThat(product.getStock()).isEqualTo(10);
    }

    @Test
    void decrementStock_shouldNotUpdateWhenInsufficientStock() {
        var product = em.persist(new Product("Low Stock", "desc", new BigDecimal("10.00"), 2, "Test"));
        em.flush();

        int updated = productRepository.decrementStock(product.getId(), 10);

        assertThat(updated).isEqualTo(0);
    }

    @Test
    void existsByName_shouldReturnTrueForExistingProduct() {
        assertThat(productRepository.existsByName("Laptop Pro")).isTrue();
    }

    @Test
    void existsByName_shouldReturnFalseForNonExistingProduct() {
        assertThat(productRepository.existsByName("Nonexistent Product")).isFalse();
    }
}

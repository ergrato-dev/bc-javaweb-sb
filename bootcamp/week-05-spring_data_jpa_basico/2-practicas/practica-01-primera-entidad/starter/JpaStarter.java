package com.bootcamp;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Práctica 01 — Mi Primera Entidad JPA
 *
 * Instrucciones: descomenta cada sección en orden.
 * Usa la H2 Console en http://localhost:8080/h2-console para verificar.
 */
@SpringBootApplication
public class JpaStarter {
    public static void main(String[] args) {
        SpringApplication.run(JpaStarter.class, args);
    }

    // ============================================
    // STEP 4: Guardar datos al iniciar la app
    // Descomenta este CommandLineRunner:
    // ============================================

    // @Bean
    // CommandLineRunner seedData(ProductRepository repo) {
    //     return args -> {
    //         repo.save(new Product("Laptop",        new BigDecimal("999.99"),  10, "Electronics"));
    //         repo.save(new Product("Wireless Mouse", new BigDecimal("29.99"),   50, "Electronics"));
    //         repo.save(new Product("Desk Chair",    new BigDecimal("199.99"),   5, "Furniture"));
    //
    //         System.out.println("✅ Products saved: " + repo.count());
    //
    //         // STEP 5: Consultar por categoría — descomenta:
    //         // var electronics = repo.findByCategory("Electronics");
    //         // System.out.println("Electronics products: " + electronics.size());
    //         // electronics.forEach(p -> System.out.println("  - " + p.getName()));
    //
    //         // Probar existsByName:
    //         // System.out.println("Laptop exists: " + repo.existsByName("Laptop"));
    //         // System.out.println("Monitor exists: " + repo.existsByName("Monitor"));
    //     };
    // }
}

// ============================================
// STEP 2: Crear la entidad JPA
// Descomenta las anotaciones @Entity, @Table, @Id, @GeneratedValue, @Column:
// ============================================

// @Entity
// @Table(name = "products")
class Product {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false)
    private String name;

    // @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // @Column(nullable = false)
    private Integer stock;

    // @Column
    private String category;

    protected Product() {}   // JPA requiere constructor sin args

    public Product(String name, BigDecimal price, Integer stock, String category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public Integer getStock() { return stock; }
    public String getCategory() { return category; }
}

// ============================================
// STEP 3: Agregar Derived Query Methods al repository
// Descomenta los métodos findByCategory y existsByName:
// ============================================

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {
    // List<Product> findByCategory(String category);
    // boolean existsByName(String name);
}

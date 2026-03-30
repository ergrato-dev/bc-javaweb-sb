package com.bootcamp;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Práctica 02 — Paginación y @Query
 *
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class PaginationApp {
    public static void main(String[] args) {
        SpringApplication.run(PaginationApp.class, args);
    }

    @Bean
    CommandLineRunner seedData(ProdRepository repo) {
        return args -> {
            repo.save(new Prod("Laptop Pro",    new BigDecimal("1299.99"), "Electronics"));
            repo.save(new Prod("Wireless Mouse",new BigDecimal("39.99"),   "Electronics"));
            repo.save(new Prod("USB-C Hub",     new BigDecimal("49.99"),   "Electronics"));
            repo.save(new Prod("Desk Chair",    new BigDecimal("249.99"), "Furniture"));
            repo.save(new Prod("Standing Desk", new BigDecimal("599.99"), "Furniture"));
            repo.save(new Prod("Notebook",      new BigDecimal("12.99"),  "Stationery"));
            repo.save(new Prod("Ballpoint Pen", new BigDecimal("3.99"),   "Stationery"));
        };
    }
}

@Entity
@Table(name = "prods")
class Prod {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false, precision = 10, scale = 2) private BigDecimal price;
    @Column private String category;

    protected Prod() {}
    public Prod(String name, BigDecimal price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
}

@Repository
interface ProdRepository extends JpaRepository<Prod, Long> {

    // ============================================
    // STEP 3: Búsqueda por nombre parcial con JPQL
    // Descomenta este método:
    // ============================================
    // @Query("SELECT p FROM Prod p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    // List<Prod> searchByName(@Param("name") String name);

    // ============================================
    // STEP 4: Filtrar por precio máximo
    // Descomenta este método:
    // ============================================
    // @Query("SELECT p FROM Prod p WHERE p.price <= :maxPrice ORDER BY p.price ASC")
    // List<Prod> findByMaxPrice(@Param("maxPrice") BigDecimal maxPrice);
}

@Service
@Transactional(readOnly = true)
class ProdService {
    private final ProdRepository repo;
    public ProdService(ProdRepository repo) { this.repo = repo; }

    // STEP 2: Cambia List<Prod> a Page<Prod> y agrega Pageable al parámetro:
    public List<Prod> findAll() { return repo.findAll(); }

    // STEP 3: Descomenta:
    // public List<Prod> searchByName(String name) { return repo.searchByName(name); }

    // STEP 4: Descomenta:
    // public List<Prod> findByMaxPrice(BigDecimal maxPrice) { return repo.findByMaxPrice(maxPrice); }
}

@RestController
@RequestMapping("/api/products")
class ProdController {
    private final ProdService service;
    public ProdController(ProdService service) { this.service = service; }

    // ============================================
    // STEP 2: Agregar Pageable y retornar Page<Prod>
    // Reemplaza la firma del método (actualmente sin paginación):
    // ============================================
    @GetMapping
    public List<Prod> getAll() { return service.findAll(); }
    // public Page<Prod> getAll(Pageable pageable) { return service.findAll(pageable); }

    // ============================================
    // STEP 3: Endpoint de búsqueda
    // Descomenta:
    // ============================================
    // @GetMapping("/search")
    // public List<Prod> search(@RequestParam String name) {
    //     return service.searchByName(name);
    // }

    // ============================================
    // STEP 4: Endpoint filtro por precio
    // Descomenta:
    // ============================================
    // @GetMapping("/cheap")
    // public List<Prod> cheap(@RequestParam BigDecimal maxPrice) {
    //     return service.findByMaxPrice(maxPrice);
    // }
}

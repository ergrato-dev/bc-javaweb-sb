package com.bootcamp;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Práctica 02 — @Transactional y Auditoría
 *
 * Instrucciones: descomenta cada sección para observar el comportamiento.
 */
// ============================================
// STEP 4: Habilitar JPA Auditing
// Descomenta @EnableJpaAuditing:
// ============================================
// @EnableJpaAuditing
@SpringBootApplication
public class TransactionApp {
  public static void main(String[] args) {
    SpringApplication.run(TransactionApp.class, args);
  }

  // STEP 4: Bean para AuditorAware (quién realiza la operación)
  // @Bean
  // AuditorAware<String> auditorProvider() {
  // return () -> Optional.of("system");
  // }
}

@Entity
@Table(name = "blog_posts")
// STEP 4: Agregar @EntityListeners:
// @EntityListeners(AuditingEntityListener.class)
class BlogPost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String title;
  @Column(columnDefinition = "TEXT")
  private String content;

  // ============================================
  // STEP 4: Agregar campos de auditoría
  // Descomenta:
  // ============================================
  // @CreatedDate
  // @Column(name = "created_at", updatable = false)
  // private LocalDateTime createdAt;
  //
  // @LastModifiedDate
  // @Column(name = "updated_at")
  // private LocalDateTime updatedAt;

  protected BlogPost() {
  }

  public BlogPost(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}

@Repository
interface BlogPostRepo extends JpaRepository<BlogPost, Long> {
}

// ============================================
// STEP 2: Agregar @Transactional al service
// STEP 3: Cambiar a @Transactional(readOnly = true) a nivel de clase
// ============================================
@Service
class BlogPostService {
  private final BlogPostRepo repo;

  public BlogPostService(BlogPostRepo repo) {
    this.repo = repo;
  }

  public List<BlogPost> findAll() {
    return repo.findAll();
  }

  // @Transactional ← STEP 2
  public BlogPost create(String title, String content) {
    var post = repo.save(new BlogPost(title, content));
    // Simula operación adicional que puede fallar:
    if (title.contains("fail")) {
      throw new RuntimeException("Simulated failure after save!");
    }
    return post;
  }
}

@RestController
@RequestMapping("/api/posts")
class BlogPostController {
  private final BlogPostService service;

  public BlogPostController(BlogPostService service) {
    this.service = service;
  }

  @GetMapping
  public List<BlogPost> getAll() {
    return service.findAll();
  }

  @PostMapping
  public ResponseEntity<BlogPost> create(@RequestBody java.util.Map<String, String> body) {
    var post = service.create(body.get("title"), body.get("content"));
    return ResponseEntity.created(URI.create("/api/posts/" + post.getId())).body(post);
  }

  @PostMapping("/fail-test")
  public String failTest() {
    try {
      service.create("fail-title", "content");
    } catch (RuntimeException e) {
      return "Exception caught: " + e.getMessage() + ". Check DB for partial data.";
    }
    return "OK";
  }
}

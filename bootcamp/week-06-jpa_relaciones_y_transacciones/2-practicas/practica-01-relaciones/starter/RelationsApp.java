package com.bootcamp;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Práctica 01 — Relaciones OneToMany y ManyToOne
 *
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class RelationsApp {
  public static void main(String[] args) {
    SpringApplication.run(RelationsApp.class, args);
  }

  @Bean
  CommandLineRunner seed(AuthorRepo authorRepo, PostRepo postRepo) {
    return args -> {
      var alice = authorRepo.save(new Author("Alice Johnson", "alice@blog.com"));
      var bob = authorRepo.save(new Author("Bob Smith", "bob@blog.com"));

      // ============================================
      // STEP 4: Usar método helper addComment
      // Descomenta este bloque:
      // ============================================
      // var post1 = new Post("Spring Boot Tips", "Learn Spring Boot快", alice);
      // post1.addComment(new Comment("Great article!", "reader1"));
      // post1.addComment(new Comment("Very helpful", "reader2"));
      // postRepo.save(post1);
      //
      // var post2 = new Post("JPA Relations Guide", "Understanding JPA", bob);
      // postRepo.save(post2);

      // Sin addComment (paso inicial):
      postRepo.save(new Post("Spring Boot Tips", "Learn Spring Boot", alice));
      postRepo.save(new Post("JPA Relations Guide", "Understanding JPA", bob));

      System.out.println("Saved authors: " + authorRepo.count());
      System.out.println("Saved posts: " + postRepo.count());
    };
  }
}

@Entity
@Table(name = "authors")
class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false, unique = true)
  private String email;

  // ============================================
  // STEP 2: Agregar relación @OneToMany
  // Descomenta:
  // ============================================
  // @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval =
  // true)
  // private List<Post> posts = new ArrayList<>();

  protected Author() {
  }

  public Author(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}

@Entity
@Table(name = "posts")
class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String title;
  @Column(columnDefinition = "TEXT")
  private String content;

  // ============================================
  // STEP 2: Agregar @ManyToOne al lado owning
  // Descomenta:
  // ============================================
  // @ManyToOne(fetch = FetchType.LAZY)
  // @JoinColumn(name = "author_id")
  private Author author;

  // ============================================
  // STEP 3: Agregar @OneToMany para comments
  // Descomenta:
  // ============================================
  // @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval =
  // true)
  // private List<Comment> comments = new ArrayList<>();

  protected Post() {
  }

  public Post(String title, String content, Author author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  // ============================================
  // STEP 4: Método helper para bidireccionalidad
  // Descomenta:
  // ============================================
  // public void addComment(Comment comment) {
  // comments.add(comment);
  // comment.setPost(this);
  // }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Author getAuthor() {
    return author;
  }
}

@Entity
@Table(name = "comments")
class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 1000)
  private String content;
  @Column(name = "author_name")
  private String authorName;

  // ============================================
  // STEP 3: Agregar @ManyToOne al Comment
  // Descomenta:
  // ============================================
  // @ManyToOne(fetch = FetchType.LAZY)
  // @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  protected Comment() {
  }

  public Comment(String content, String authorName) {
    this.content = content;
    this.authorName = authorName;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}

@Repository
interface AuthorRepo extends JpaRepository<Author, Long> {
}

@Repository
interface PostRepo extends JpaRepository<Post, Long> {
  // ============================================
  // STEP 5: Query JOIN FETCH para evitar N+1
  // Descomenta:
  // ============================================
  // @Query("SELECT p FROM Post p JOIN FETCH p.author")
  // List<Post> findAllWithAuthors();
}

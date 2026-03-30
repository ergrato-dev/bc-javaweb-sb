package com.bootcamp.repository;

import com.bootcamp.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

  /**
   * TODO:
   * Add a method to find all published posts with pagination.
   * Derived: findByPublishedTrue(Pageable pageable)
   */

  /**
   * TODO:
   * Add a @Query with JOIN FETCH to load Post + Author in one query.
   * JPQL: SELECT p FROM Post p JOIN FETCH p.author WHERE p.id = :id
   */

  /**
   * TODO:
   * Add a @Query with LEFT JOIN FETCH to load Post + Author + Comments.
   * (Use DISTINCT to avoid duplicate posts)
   * JPQL: SELECT DISTINCT p FROM Post p JOIN FETCH p.author LEFT JOIN FETCH
   * p.comments WHERE p.id = :id
   */
}

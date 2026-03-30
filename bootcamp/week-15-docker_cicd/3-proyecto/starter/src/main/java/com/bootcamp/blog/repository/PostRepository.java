package com.bootcamp.blog.repository;

import com.bootcamp.blog.domain.Post;
import com.bootcamp.blog.domain.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findByStatusOrderByCreatedAtDesc(PostStatus status);

  @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.id = :id")
  java.util.Optional<Post> findByIdWithComments(Long id);
}

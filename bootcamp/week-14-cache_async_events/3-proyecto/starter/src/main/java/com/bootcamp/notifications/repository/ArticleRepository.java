package com.bootcamp.notifications.repository;

import com.bootcamp.notifications.domain.Article;
import com.bootcamp.notifications.domain.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findByStatus(ArticleStatus status);

  List<Article> findByAuthorEmail(String authorEmail);

  List<Article> findByCategory(String category);

  long countByAuthorEmail(String authorEmail);
}

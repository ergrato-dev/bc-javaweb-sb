package com.bootcamp.notifications.service;

import com.bootcamp.notifications.domain.Article;
import com.bootcamp.notifications.domain.ArticleStatus;
import com.bootcamp.notifications.dto.Dtos.*;
import com.bootcamp.notifications.event.ArticleCreatedEvent;
import com.bootcamp.notifications.event.ArticlePublishedEvent;
import com.bootcamp.notifications.exception.ArticleNotFoundException;
import com.bootcamp.notifications.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public List<ArticleResponse> findAll() {
        return articleRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "articles", key = "#id")
    public ArticleResponse findById(Long id) {
        log.info("Cache miss — consultando BD para artículo {}", id);
        return articleRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> findByStatus(ArticleStatus status) {
        return articleRepository.findByStatus(status).stream()
                .map(this::toResponse)
                .toList();
    }

    @CachePut(value = "articles", key = "#result.id()")
    public ArticleResponse create(ArticleCreateRequest request) {
        var article = Article.builder()
                .title(request.title())
                .content(request.content())
                .authorEmail(request.authorEmail())
                .category(request.category())
                .status(ArticleStatus.DRAFT)
                .build();

        var saved = articleRepository.save(article);
        eventPublisher.publishEvent(
                new ArticleCreatedEvent(saved.getId(), saved.getAuthorEmail(), saved.getTitle()));

        return toResponse(saved);
    }

    @CacheEvict(value = "articles", key = "#id")
    public ArticleResponse update(Long id, ArticleUpdateRequest request) {
        var article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
        article.setTitle(request.title());
        article.setContent(request.content());
        article.setCategory(request.category());
        return toResponse(articleRepository.save(article));
    }

    @CachePut(value = "articles", key = "#id")
    public ArticleResponse publish(Long id) {
        var article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        if (article.getStatus() != ArticleStatus.DRAFT) {
            throw new IllegalStateException(
                    "Only DRAFT articles can be published. Current status: " + article.getStatus());
        }
        article.publish();
        var saved = articleRepository.save(article);

        eventPublisher.publishEvent(new ArticlePublishedEvent(
                saved.getId(), saved.getAuthorEmail(), saved.getTitle(), saved.getCategory()));

        return toResponse(saved);
    }

    @CacheEvict(value = "articles", key = "#id")
    public void delete(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ArticleNotFoundException(id);
        }
        articleRepository.deleteById(id);
    }

    private ArticleResponse toResponse(Article a) {
        return new ArticleResponse(
                a.getId(), a.getTitle(), a.getContent(),
                a.getAuthorEmail(), a.getCategory(), a.getStatus(),
                a.getCreatedAt(), a.getPublishedAt());
    }
}

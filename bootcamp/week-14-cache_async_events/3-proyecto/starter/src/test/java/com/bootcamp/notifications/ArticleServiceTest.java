package com.bootcamp.notifications;

import com.bootcamp.notifications.domain.ArticleStatus;
import com.bootcamp.notifications.dto.Dtos.*;
import com.bootcamp.notifications.event.ArticleCreatedEvent;
import com.bootcamp.notifications.event.ArticlePublishedEvent;
import com.bootcamp.notifications.exception.ArticleNotFoundException;
import com.bootcamp.notifications.repository.ArticleRepository;
import com.bootcamp.notifications.service.ArticleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

/**
 * ArticleServiceTest — Tests unitarios del ArticleService.
 *
 * Tests a implementar:
 * 1. create() — verifica que se publique ArticleCreatedEvent con ArgumentCaptor
 * 2. findById() — happy path (cacheable verificado por SOLO un llamado al repo)
 * 3. findById() — lanza ArticleNotFoundException cuando no existe
 * 4. publish() — happy path (DRAFT → PUBLISHED + ArticlePublishedEvent)
 * 5. publish() — lanza IllegalStateException si ya está PUBLISHED
 * 6. delete() — llama a deleteById cuando existe
 * 7. delete() — lanza ArticleNotFoundException cuando no existe
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ArticleService Unit Tests")
class ArticleServiceTest {

  @InjectMocks
  private ArticleService articleService;

  @Mock
  private ArticleRepository articleRepository;

  @Mock
  private ApplicationEventPublisher eventPublisher;

  @Captor
  private ArgumentCaptor<Object> eventCaptor;

  // ============================================
  // TODO: Implementar — create() publica ArticleCreatedEvent
  // ============================================
  @Test
  @DisplayName("create() saves article and publishes ArticleCreatedEvent")
  void create_publishesEvent() {
    // TODO:
    // given:
    // - articleRepository.save(any()) retorna artículo con id=1
    // when:
    // - articleService.create(new ArticleCreateRequest(...))
    // then:
    // - verify: eventPublisher.publishEvent(eventCaptor.capture()) fue llamado
    // - assertThat(eventCaptor.getValue()).isInstanceOf(ArticleCreatedEvent.class)
    // - cast y verificar authorEmail
  }

  // ============================================
  // TODO: Implementar — findById() happy path
  // ============================================
  @Test
  @DisplayName("findById() returns ArticleResponse when article exists")
  void findById_returnsResponse() {
    // TODO:
    // given: articleRepository.findById(1L) retorna Optional.of(article)
    // when: articleService.findById(1L)
    // then: resultado.id() == 1L
  }

  // ============================================
  // TODO: Implementar — findById() not found
  // ============================================
  @Test
  @DisplayName("findById() throws ArticleNotFoundException when not found")
  void findById_throwsWhenNotFound() {
    // TODO:
    // given: articleRepository.findById(99L) → Optional.empty()
    // then: assertThatThrownBy con ArticleNotFoundException
  }

  // ============================================
  // TODO: Implementar — publish() happy path
  // ============================================
  @Test
  @DisplayName("publish() changes status to PUBLISHED and fires event")
  void publish_changesStatusAndPublishesEvent() {
    // TODO:
    // given: artículo en estado DRAFT
    // when: articleService.publish(id)
    // then:
    // - el artículo retornado tiene status PUBLISHED
    // - publishedAt no es null
    // - eventPublisher.publishEvent() fue llamado con ArticlePublishedEvent
  }

  // ============================================
  // TODO: Implementar — publish() falla si no está en DRAFT
  // ============================================
  @Test
  @DisplayName("publish() throws IllegalStateException when article is not DRAFT")
  void publish_throwsWhenNotDraft() {
    // TODO:
    // given: artículo en estado PUBLISHED (ya publicado)
    // then: assertThatThrownBy lanza IllegalStateException
    // con mensaje que menciona "DRAFT"
  }

  // ============================================
  // TODO: Implementar — delete() happy path
  // ============================================
  @Test
  @DisplayName("delete() calls deleteById when article exists")
  void delete_callsDeleteById() {
    // TODO:
    // given: articleRepository.existsById(1L) retorna true
    // when: articleService.delete(1L)
    // then: verify(articleRepository).deleteById(1L) fue llamado
  }

  // ============================================
  // TODO: Implementar — delete() not found
  // ============================================
  @Test
  @DisplayName("delete() throws ArticleNotFoundException when article not found")
  void delete_throwsWhenNotFound() {
    // TODO:
    // given: articleRepository.existsById(99L) retorna false
    // then: assertThatThrownBy lanza ArticleNotFoundException
  }
}

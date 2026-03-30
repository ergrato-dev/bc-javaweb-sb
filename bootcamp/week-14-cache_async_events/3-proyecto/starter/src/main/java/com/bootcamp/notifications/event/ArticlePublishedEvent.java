package com.bootcamp.notifications.event;

/**
 * Evento publicado cuando un artículo es publicado (cambio de estado DRAFT →
 * PUBLISHED).
 */
public record ArticlePublishedEvent(Long articleId, String authorEmail, String title, String category) {
}

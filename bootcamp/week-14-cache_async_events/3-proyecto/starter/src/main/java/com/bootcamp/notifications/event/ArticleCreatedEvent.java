package com.bootcamp.notifications.event;

/**
 * Evento publicado cuando un artículo es creado.
 * Record inmutable — no puede ser modificado después de publicado.
 */
public record ArticleCreatedEvent(Long articleId, String authorEmail, String title) {
}

package com.bootcamp.notifications.service;

import com.bootcamp.notifications.event.ArticleCreatedEvent;
import com.bootcamp.notifications.event.ArticlePublishedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * NotificationListener — Escucha eventos del dominio de articulos.
 *
 * Cumple con el principio Open/Closed:
 * - ArticleService NO necesita modificarse para agregar nuevas notificaciones
 * - Solo se agrega un nuevo @EventListener en este componente (o un nuevo componente)
 */
@Component
@Slf4j
public class NotificationListener {

    // ============================================
    // TODO: Implementar — onArticleCreated
    // ============================================
    // 1. Agregar @Async @EventListener
    // 2. El método recibe ArticleCreatedEvent como parámetro
    // 3. Simular envío de email al autor (Thread.sleep(1000) + log.info)
    // 4. Log: "[Email] Bienvenida enviada a {email} por artículo: {title}"
    // 5. Verificar en los logs que el thread es "async-N" (no el principal)

    // ============================================
    // TODO: Implementar — onArticlePublished
    // ============================================
    // 1. Agregar @Async @EventListener
    // 2. El método recibe ArticlePublishedEvent como parámetro
    // 3. Simular notificación a suscriptores de la categoría (Thread.sleep(500))
    // 4. Log: "[Notification] Artículo publicado en categoría {category}: {title}"
    // 5. Log: "[Notification] Notificando a suscriptores de la categoría {category}"

    // ============================================
    // TODO: Implementar — scheduledCleanup
    // ============================================
    // 1. Agregar @Scheduled(fixedRate = 60_000) — cada 60 segundos
    // 2. Log: "[Scheduled] Procesando notificaciones pendientes..."
    // 3. Este método representa el procesamiento de una cola de notificaciones diferidas
}

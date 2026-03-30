package com.bootcamp.notifications;

import com.bootcamp.notifications.event.ArticleCreatedEvent;
import com.bootcamp.notifications.event.ArticlePublishedEvent;
import com.bootcamp.notifications.service.NotificationListener;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

/**
 * NotificationListenerTest — Tests del listener de eventos.
 *
 * NOTA: Los event listeners se testean en unidad llamando el método directamente.
 * No necesitamos publicar el evento — solo invocamos onArticleCreated() / onArticlePublished().
 *
 * Tests a implementar:
 * 1. onArticleCreated() — se llama y no lanza excepciones (smoke test)
 * 2. onArticlePublished() — se llama y no lanza excepciones
 * 3. (Opcional avanzado) Verificar que se loggea correctamente usando @Spy
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("NotificationListener Tests")
class NotificationListenerTest {

    // Usamos Spy para observar el comportamiento sin mockear completamente
    @Spy
    private NotificationListener notificationListener;

    // ============================================
    // TODO: Implementar — onArticleCreated smoke test
    // ============================================
    @Test
    @DisplayName("onArticleCreated does not throw when called with valid event")
    void onArticleCreated_doesNotThrow() {
        // TODO:
        // given: var event = new ArticleCreatedEvent(1L, "author@test.com", "Test Article");
        // then: assertThatCode(() -> notificationListener.onArticleCreated(event))
        //           .doesNotThrowAnyException();
        // Nota: El método debe estar implementado en NotificationListener para que compile
    }

    // ============================================
    // TODO: Implementar — onArticlePublished smoke test
    // ============================================
    @Test
    @DisplayName("onArticlePublished does not throw when called with valid event")
    void onArticlePublished_doesNotThrow() {
        // TODO: similar al anterior pero con ArticlePublishedEvent
    }
}

package com.bootcamp.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Smoke test — verifica que el contexto de Spring Boot carga correctamente.
 * Usa el perfil "test" que configura H2 en lugar de PostgreSQL.
 */
@SpringBootTest
@ActiveProfiles("test")
class BlogApiApplicationTest {

    @Test
    void contextLoads() {
        // Si este test pasa, la configuración de la aplicación es correcta
    }
}

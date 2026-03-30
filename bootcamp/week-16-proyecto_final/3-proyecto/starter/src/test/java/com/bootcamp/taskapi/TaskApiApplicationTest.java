package com.bootcamp.taskapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Smoke test: verifica que el contexto de Spring Boot levanta sin errores.
 * Usa el perfil "test" que configura H2 en lugar de PostgreSQL.
 */
@SpringBootTest
@ActiveProfiles("test")
class TaskApiApplicationTest {

    @Test
    void contextLoads() {
        // Si llega aquí, Spring Boot levantó correctamente
    }
}

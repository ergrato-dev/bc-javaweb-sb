# Rúbrica de Evaluación — Semana 14: Cache, Async y Events

## Proyecto: Notifications API

### 🧠 Conocimiento (30%)

| Criterio | Excelente | Satisfactorio | Insuficiente |
|----------|-----------|---------------|--------------|
| Spring Cache | Explica la diferencia entre `@Cacheable` y `@CachePut` y cuándo usar `@CacheEvict` | Conoce las tres anotaciones pero confunde cuándo usar cada una | Solo conoce `@Cacheable` |
| @Async y threading | Explica qué es el thread pool, self-invocation problem y cuándo NO usar `@Async` | Sabe cómo anotar un método pero no conoce las restricciones | No sabe la diferencia entre sync y async |
| Application Events | Puede explicar el desacoplamiento que brindan los eventos vs. llamada directa | Sabe publicar y escuchar eventos pero no domina `@TransactionalEventListener` | No sabe cómo publicar un evento |

### 💪 Desempeño (40%)

| Criterio | Puntos | Descripción |
|----------|--------|-------------|
| `NotificationListener` — `onArticleCreated` | 10 pts | Implementado con `@Async @EventListener`, log correcto, no bloquea |
| `NotificationListener` — `onArticlePublished` | 10 pts | Implementado correctamente |
| `NotificationListener` — `scheduledCleanup` | 10 pts | `@Scheduled(fixedRate=60_000)`, log correcto |
| `ArticleServiceTest` — create con ArgumentCaptor | 10 pts | Verifica que se publica el evento con los datos correctos |

### 📦 Producto (30%)

| Criterio | Puntos | Descripción |
|----------|--------|-------------|
| `mvn test` — BUILD SUCCESS | 10 pts | Todos los tests pasan |
| Logs de async | 10 pts | Los logs muestran `thread: async-N` en operaciones asíncronas |
| Cobertura JaCoCo ≥ 70% en service | 10 pts | Reporte generado y cobertura verificada |

---

## Escala de Calificación

| Puntos | Calificación |
|--------|-------------|
| 90–100 | Excelente |
| 80–89 | Muy bien |
| 70–79 | Aprobado |
| < 70 | Insuficiente — revisar teoría de @Async y Application Events |

---

## Checklist del Estudiante

- [ ] `NotificationListener` — 3 métodos implementados
- [ ] `ArticleServiceTest` — 7 tests pasando
- [ ] `NotificationListenerTest` — 2 smoke tests pasando
- [ ] `mvn test` → `BUILD SUCCESS`
- [ ] Logs visibles de threads async en consola
- [ ] Reporte JaCoCo generado
